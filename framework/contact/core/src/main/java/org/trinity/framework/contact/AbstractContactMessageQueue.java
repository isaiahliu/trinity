package org.trinity.framework.contact;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.trinity.common.util.Tuple3;

public abstract class AbstractContactMessageQueue<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>, TSerializer extends IContactMessageSerializer<TMessageMeta, TMessage>, TDeserializer extends IContactMessageDeserializer<TMessageMeta, TMessage>>
        implements IContactMessageQueue<TMessageMeta, TMessage, TSerializer, TDeserializer> {
    public static final class MessageStatus {
        private long lastSendTime;
        private int resendTimes;

        public MessageStatus() {
            lastSendTime = new Date().getTime();
            resendTimes = 0;
        }

        public long getLastSendTime() {
            return lastSendTime;
        }

        public int getResendTimes() {
            return resendTimes;
        }

        public void notifyResend() {
            lastSendTime = new Date().getTime();
            resendTimes++;
        }
    }

    public static final class SubPackage {
        private long lastReceiveTime;
        private int receiveTimes;
        private final byte[][] packages;

        public SubPackage(final int packageCount) {
            super();
            packages = new byte[packageCount][];
            lastReceiveTime = new Date().getTime();
            receiveTimes = 0;
        }

        public void addSubPackageBody(final int packageIndex, final byte[] body) {
            packages[packageIndex - 1] = body;
        }

        public long getLastReceiveTime() {
            return lastReceiveTime;
        }

        public List<Integer> getMissingIndexes() {
            final List<Integer> missingIndexes = new ArrayList<>();
            for (int index = 0; index < packages.length; index++) {
                if (packages[index] == null) {
                    missingIndexes.add(index + 1);
                }
            }

            return missingIndexes;
        }

        public int getReceiveTimes() {
            return receiveTimes;
        }

        public boolean isAllPackagesReady() {
            for (final byte[] l : packages) {
                if (l == null) {
                    return false;
                }
            }
            return true;
        }

        public byte[] mergeMessageCode() {
            try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                for (final byte[] b : packages) {
                    output.write(b);
                }

                return output.toByteArray();
            } catch (final IOException e) {
                return new byte[0];
            }
        }

        public void notifyReceive() {
            lastReceiveTime = new Date().getTime();
            receiveTimes++;
        }
    }

    private volatile int currentSerialNumber = 0;
    private final int maxSerialNumber;
    private final int maxBodyLength;
    private final int timeOutSenconds;
    private final int maxRetryTimes;

    private final Map<Integer, Tuple3<TMessage, byte[], MessageStatus>> sentMessages;
    private final Map<Integer, SubPackage> subPackages;

    private final TDeserializer deserializer;

    private final TSerializer serializer;

    private boolean active;

    public AbstractContactMessageQueue(final TSerializer serializer, final TDeserializer deserializer,
            final int maxSerialNumber, final int maxBodyLength, final int timeOutSenconds, final int maxRetryTimes) {
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.maxSerialNumber = maxSerialNumber;
        this.maxBodyLength = maxBodyLength;
        this.timeOutSenconds = timeOutSenconds;
        this.maxRetryTimes = maxRetryTimes;
        sentMessages = new HashMap<>();
        subPackages = new HashMap<>();
    }

    @Override
    public byte[] getCodesFromMessages(final TMessage message) {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            final byte[] body = serializer.serializeBody(message);

            if (allowSubPackages() && body.length > maxBodyLength) {
                final int packageCount = (body.length - 1) / maxBodyLength + 1;

                int serialNumber = getNextSerialNumber(packageCount);

                message.getMeta().setBodyLength(maxBodyLength);
                message.getMeta().setSerialNumber(serialNumber);
                message.getMeta().setFirstPackageSerialNumber(serialNumber);
                message.getMeta().setPackageCount(packageCount);

                byte[] header = serializer.serializeHeader(message.getMeta());
                try (ByteArrayOutputStream packageCodes = new ByteArrayOutputStream()) {
                    packageCodes.write(header);
                    packageCodes.write(body, 0, maxBodyLength);

                    final byte[] byteArray = packageCodes.toByteArray();
                    if (hasVerifyCode()) {
                        packageCodes.write(getVerifyCode(byteArray));
                    }

                    if (message.isRequireResponse()) {
                        sentMessages.put(message.getMeta().getSerialNumber(),
                                new Tuple3<>(message, byteArray, new MessageStatus()));
                    }

                    result.write(escape(byteArray));
                }

                int startIndex = maxBodyLength;
                int restSize = body.length - maxBodyLength;
                int packageIndex = 0;
                while (restSize > 0) {
                    packageIndex++;
                    serialNumber++;

                    if (serialNumber >= maxSerialNumber) {
                        serialNumber -= maxSerialNumber;
                    }

                    final int packageBodyLength = restSize < maxBodyLength ? restSize : maxBodyLength;

                    final TMessage packageMessage = createSubPackageMessage(message.isRequireResponse());

                    copyMeta(message.getMeta(), packageMessage.getMeta());
                    packageMessage.getMeta().setId(message.getMeta().getId());
                    packageMessage.getMeta().setSerialNumber(serialNumber);
                    packageMessage.getMeta().setBodyLength(packageBodyLength);
                    packageMessage.getMeta().setFirstPackageSerialNumber(message.getMeta().getSerialNumber());
                    packageMessage.getMeta().setPackageIndex(packageIndex);
                    packageMessage.getMeta().setPackageCount(message.getMeta().getPackageCount());

                    header = serializer.serializeHeader(packageMessage.getMeta());

                    try (ByteArrayOutputStream packageCodes = new ByteArrayOutputStream()) {
                        packageCodes.write(header);
                        packageCodes.write(body, startIndex, packageBodyLength);

                        if (hasVerifyCode()) {
                            packageCodes.write(getVerifyCode(packageCodes.toByteArray()));
                        }

                        final byte[] escape = escape(packageCodes.toByteArray());
                        if (packageMessage.isRequireResponse()) {
                            sentMessages.put(packageMessage.getMeta().getSerialNumber(),
                                    new Tuple3<>(packageMessage, escape, new MessageStatus()));
                        }

                        result.write(escape);
                    }
                    restSize -= packageBodyLength;
                    startIndex += packageBodyLength;
                }
            } else {
                message.getMeta().setBodyLength(body.length);

                if (message.getMeta().getSerialNumber() < 0) {
                    message.getMeta().setSerialNumber(getNextSerialNumber());
                }

                final byte[] header = serializer.serializeHeader(message.getMeta());
                try (ByteArrayOutputStream packageCodes = new ByteArrayOutputStream()) {
                    packageCodes.write(header);
                    packageCodes.write(body);
                    if (hasVerifyCode()) {
                        packageCodes.write(getVerifyCode(packageCodes.toByteArray()));
                    }
                    final byte[] escape = escape(packageCodes.toByteArray());

                    if (message.isRequireResponse()) {
                        sentMessages.put(message.getMeta().getSerialNumber(),
                                new Tuple3<>(message, escape, new MessageStatus()));
                    }

                    result.write(escape);
                }
            }

            return result.toByteArray();
        } catch (final IOException e) {
        }
        return new byte[0];
    }

    public TDeserializer getDeserializer() {
        return deserializer;
    }

    public int getMaxBodyLength() {
        return maxBodyLength;
    }

    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public int getMaxSerialNumber() {
        return maxSerialNumber;
    }

    @Override
    public List<TMessage> getMessagesFromCodes(final byte[] messageCodes) {

        final List<byte[]> messageCodesList = unescape(messageCodes);

        final List<TMessage> result = new ArrayList<>();

        for (final byte[] messageCode : messageCodesList) {
            try {
                ByteArrayInputStream messageStream = null;
                if (hasVerifyCode()) {
                    final byte verifyCode = getVerifyCode(messageCode);

                    messageStream = new ByteArrayInputStream(messageCode, 0, messageCode.length - 1);
                    if (verifyCode != 0) {

                        final TMessageMeta header = deserializer.deserializeHeader(messageStream);

                        final TMessage badRequest = createBadRequestMessage();

                        badRequest.setMeta(header);

                        result.add(badRequest);

                        continue;
                    }
                } else {
                    messageStream = new ByteArrayInputStream(messageCode);
                }

                final TMessageMeta header = deserializer.deserializeHeader(messageStream);

                if (allowSubPackages() && header.getPackageCount() > 1) {
                    final int serialNumber = header.getSerialNumber();
                    final int packageIndex = header.getPackageIndex();
                    int firstSerialNumber = serialNumber - packageIndex + 1;
                    if (firstSerialNumber < 0) {
                        firstSerialNumber += getMaxSerialNumber();
                    }

                    if (!getSubPackages().containsKey(firstSerialNumber)) {
                        getSubPackages().put(firstSerialNumber, new SubPackage(header.getPackageCount()));
                    }

                    final SubPackage subPackage = getSubPackages().get(firstSerialNumber);

                    final byte[] temp = new byte[messageStream.available()];
                    messageStream.read(temp);
                    subPackage.addSubPackageBody(packageIndex, temp);

                    if (subPackage.isAllPackagesReady()) {
                        final TMessage message = deserializer.deserializeBody(header,
                                new ByteArrayInputStream(subPackage.mergeMessageCode()));

                        message.getMeta().setSerialNumber(firstSerialNumber);

                        result.add(message);
                    }
                } else {
                    header.setOriginalMessageBytes(escape(messageCode));
                    final TMessage message = deserializer.deserializeBody(header, messageStream);

                    result.add(message);
                }
            } catch (final IOException e) {
            }
        }
        return result;
    }

    @Override
    public synchronized int getNextSerialNumber() {
        return getNextSerialNumber(1);
    }

    @Override
    public synchronized int getNextSerialNumber(final int count) {
        final int n = currentSerialNumber;

        currentSerialNumber += count;
        if (currentSerialNumber >= getMaxSerialNumber()) {
            currentSerialNumber %= getMaxSerialNumber();
        }
        return n;
    }

    @Override
    public TMessage getRequestMessage(final int serialNumber) {
        final Tuple3<TMessage, byte[], MessageStatus> tuple = getSentMessages().get(serialNumber);

        if (tuple != null) {
            return tuple.getItem1();
        }
        return null;
    }

    public Map<Integer, Tuple3<TMessage, byte[], MessageStatus>> getSentMessages() {
        return sentMessages;
    }

    public TSerializer getSerializer() {
        return serializer;
    }

    public Map<Integer, SubPackage> getSubPackages() {
        return subPackages;
    }

    public int getTimeOutSenconds() {
        return timeOutSenconds;
    }

    @Override
    public List<Integer> getTimeoutSerialNumbers() {
        final List<Integer> timeoutSerialNumbers = new ArrayList<>();
        for (final int serialNumber : getSubPackages().keySet()) {
            final SubPackage subPackage = getSubPackages().get(serialNumber);

            if (subPackage == null) {
                continue;
            }

            final long now = new Date().getTime();

            if (now - subPackage.getLastReceiveTime() > getTimeOutSenconds() * 1000) {
                subPackage.notifyReceive();

                if (subPackage.getReceiveTimes() > getMaxRetryTimes()) {
                    getSubPackages().remove(serialNumber);
                }

                timeoutSerialNumbers.addAll(subPackage.getMissingIndexes().stream().map(item -> {
                    int subPackageSerialNumber = serialNumber + item - 1;

                    if (subPackageSerialNumber >= getMaxSerialNumber()) {
                        subPackageSerialNumber -= getMaxSerialNumber();
                    }

                    return subPackageSerialNumber;
                }).collect(Collectors.toList()));
            }
        }

        return timeoutSerialNumbers;
    }

    @Override
    public byte[] getUnresponsedSendMessage() {
        final long now = new Date().getTime();
        final long timeOut = getTimeOutSenconds() * 1000;

        try (final ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            for (final int serialNumber : getSentMessages().keySet()) {
                final Tuple3<TMessage, byte[], MessageStatus> messageTuple = getSentMessages().get(serialNumber);

                if (messageTuple != null) {
                    if (now - messageTuple.getItem3().getLastSendTime() > timeOut) {
                        messageTuple.getItem3().notifyResend();

                        if (messageTuple.getItem3().getResendTimes() > getMaxRetryTimes()) {
                            getSentMessages().remove(serialNumber);
                        } else {
                            result.write(messageTuple.getItem2());
                        }
                    }
                }
            }

            return result.toByteArray();
        } catch (final IOException e) {
            return new byte[0];
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public TMessage pollUnresponsedSentMessage(final int serialNumber) {
        final Tuple3<TMessage, byte[], MessageStatus> tuple = getSentMessages().get(serialNumber);

        if (tuple != null) {
            getSentMessages().remove(serialNumber);
            return tuple.getItem1();
        }
        return null;
    }

    @Override
    public void setActive(final String owner, final boolean active) {
        this.active = active;
    }

    protected abstract boolean allowSubPackages();

    protected abstract void copyMeta(TMessageMeta from, TMessageMeta to);

    protected abstract TMessage createBadRequestMessage();

    protected abstract TMessage createSubPackageMessage(boolean requireResponse);

    protected abstract byte[] escape(final byte[] messageCode);

    protected abstract byte getVerifyCode(byte[] byteArray);

    protected abstract boolean hasVerifyCode();

    protected abstract List<byte[]> unescape(final byte[] messageCodes);
}
