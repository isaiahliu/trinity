package org.trinity.framework.contact.tsykt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.trinity.common.util.Tuple3;
import org.trinity.framework.contact.AbstractContactMessageQueue;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageUtil;

public class TsyktMessageQueue extends
        AbstractContactMessageQueue<ITsyktMessageMeta, ITsyktMessage, ITsyktMessageSerializer, ITsyktMessageDeserializer>
        implements ITsyktMessageQueue {
    public TsyktMessageQueue(final ITsyktMessageSerializer serializer, final ITsyktMessageDeserializer deserializer,
            final int maxSerialNumber, final int maxBodyLength, final int timeOutSenconds, final int maxRetryTimes) {
        super(serializer, deserializer, maxSerialNumber, maxBodyLength, timeOutSenconds, maxRetryTimes);
    }

    @Override
    public byte[] getCodesFromMessages(final ITsyktMessage message) {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            final byte[] body = getSerializer().serializeBody(message);

            final byte[] header = getSerializer().serializeHeader(message.getMeta());
            try (ByteArrayOutputStream packageCodes = new ByteArrayOutputStream()) {
                packageCodes.write(header);
                packageCodes.write(body);
                if (hasVerifyCode()) {
                    packageCodes.write(getVerifyCode(packageCodes.toByteArray()));
                }
                final byte[] escape = escape(packageCodes.toByteArray());

                if (message.isRequireResponse()) {
                    getSentMessages().put(message.getMeta().getSerialNumber(),
                            new Tuple3<>(message, escape, new MessageStatus()));
                }

                result.write(escape);
            }

            return result.toByteArray();
        } catch (final IOException e) {
        }
        return new byte[0];
    }

    @Override
    protected boolean allowSubPackages() {
        return true;
    }

    @Override
    protected void copyMeta(final ITsyktMessageMeta from, final ITsyktMessageMeta to) {
    }

    @Override
    protected ITsyktMessage createBadRequestMessage() {
        return null;
    }

    @Override
    protected ITsyktMessage createSubPackageMessage(final boolean requireResponse) {
        return null;
    }

    @Override
    protected byte[] escape(final byte[] messageCode) {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ContactMessageUtil.write(out, messageCode.length + 2, 2, StoreMethod.BIG_END);

            out.write(messageCode);

            return out.toByteArray();
        } catch (final Exception e) {
            return new byte[0];
        }
    }

    @Override
    protected byte getVerifyCode(final byte[] byteArray) {
        int result = 0;

        for (final byte b : byteArray) {
            result ^= Byte.toUnsignedInt(b);
        }

        return (byte) result;
    }

    @Override
    protected boolean hasVerifyCode() {
        return false;
    }

    @Override
    protected List<byte[]> unescape(final byte[] messageCodes) {
        return Collections.singletonList(messageCodes);
    }
}
