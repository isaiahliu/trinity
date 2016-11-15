package org.trinity.framework.contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractContactMessagePool<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>, TSerializer extends IContactMessageSerializer<TMessageMeta, TMessage>, TDeserializer extends IContactMessageDeserializer<TMessageMeta, TMessage>, TMessageQueue extends IContactMessageQueue<TMessageMeta, TMessage, TSerializer, TDeserializer>>
        implements IContactMessagePool<TMessageMeta, TMessage, TSerializer, TDeserializer, TMessageQueue> {

    private int maxSerialNumber = ContactMessageUtil.DEFAULT_MAX_SERIAL_NUMBER;
    private int maxBodyLength = ContactMessageUtil.DEFAULT_MAX_BODY_LENGTH;
    private int timeOutSenconds = ContactMessageUtil.DEFAULT_TIMEOUT_SECONDS;
    private int maxRetryTimes = ContactMessageUtil.DEFAULT_MAX_RETRY_TIMES;

    private final Map<String, TMessageQueue> pool;

    public AbstractContactMessagePool() {
        pool = new HashMap<>();
    }

    @Override
    public byte[] getCodesFromMessages(final String owner, final TMessage message) {
        return getQueueForOwner(owner).getCodesFromMessages(message);
    }

    public abstract TDeserializer getDeserializer();

    @Override
    public byte[] getExpiredRequestMessage(final String owner) {
        return getQueueForOwner(owner).getUnresponsedSendMessage();
    }

    @Override
    public int getMaxBodyLength() {
        return maxBodyLength;
    }

    @Override
    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    @Override
    public int getMaxSerialNumber() {
        return maxSerialNumber;
    }

    @Override
    public List<TMessage> getMessagesFromCodes(final String owner, final byte[] messageCodes) {
        return getQueueForOwner(owner).getMessagesFromCodes(messageCodes);
    }

    @Override
    public Set<String> getOwners() {
        return getPool().keySet();
    }

    @Override
    public TMessage getRequestMessage(final String owner, final int serialNumber) {
        return getQueueForOwner(owner).getRequestMessage(serialNumber);
    }

    public abstract TSerializer getSerializer();

    @Override
    public int getTimeOutSenconds() {
        return timeOutSenconds;
    }

    @Override
    public List<Integer> getTimeoutSerialNumbers(final String owner) {
        return getQueueForOwner(owner).getTimeoutSerialNumbers();
    }

    @Override
    public boolean isActive(final String owner) {
        return getQueueForOwner(owner).isActive();
    }

    @Override
    public TMessage pollRequestMessage(final String owner, final int serialNumber) {
        return getQueueForOwner(owner).pollUnresponsedSentMessage(serialNumber);
    }

    @Override
    public synchronized void registerOwner(final String owner) {
        removeOwner(owner);

        setActive(owner, true);
    }

    @Override
    public synchronized void removeOwner(final String owner) {
        if (getPool().containsKey(owner)) {
            getPool().remove(owner);
        }
    }

    @Override
    public void setActive(final String owner, final boolean active) {
        getQueueForOwner(owner).setActive(null, active);
    }

    public abstract void setDeserializer(final TDeserializer deserializer);

    public void setMaxBodyLength(final int maxBodyLength) {
        this.maxBodyLength = maxBodyLength;
    }

    public void setMaxRetryTimes(final int maxResendTimes) {
        this.maxRetryTimes = maxResendTimes;
    }

    public void setMaxSerialNumber(final int maxSerialNumber) {
        this.maxSerialNumber = maxSerialNumber;
    }

    public abstract void setSerializer(final TSerializer serializer);

    public void setTimeOutSenconds(final int timeOutSenconds) {
        this.timeOutSenconds = timeOutSenconds;
    }

    protected Map<String, TMessageQueue> getPool() {
        return pool;
    }

    protected abstract TMessageQueue getQueueForOwner(final String owner);
}
