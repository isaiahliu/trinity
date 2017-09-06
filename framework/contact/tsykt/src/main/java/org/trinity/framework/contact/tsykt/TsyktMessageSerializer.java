package org.trinity.framework.contact.tsykt;

import java.io.ByteArrayOutputStream;

import org.trinity.framework.contact.AbstractContactMessageSerializer;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageUtil;

public final class TsyktMessageSerializer extends AbstractContactMessageSerializer<ITsyktMessageMeta, ITsyktMessage>
        implements ITsyktMessageSerializer {

    @Override
    public byte[] serializeBody(final ITsyktMessage message) {
        final ByteArrayOutputStream body = new ByteArrayOutputStream();

        serializeObject(message, body);
        return body.toByteArray();
    }

    @Override
    public byte[] serializeHeader(final ITsyktMessageMeta header) {
        final ByteArrayOutputStream messageCode = new ByteArrayOutputStream();

        ContactMessageUtil.write(messageCode, 0x60, 1, StoreMethod.BIG_END);
        ContactMessageUtil.writeBcd(messageCode, "61", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "22", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "00", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "00", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "00", 1, "0");
        ContactMessageUtil.writeBcd(messageCode, "00", 1, "0");

        int b = 0;
        int length = 0;
        for (final byte position : header.getBitMap()) {
            b <<= 1;
            b |= position;
            length++;
            if (length % 8 == 0) {
                messageCode.write(b);
                b = 0;
            }
        }

        ContactMessageUtil.write(messageCode, header.getId(), 2, StoreMethod.BIG_END);

        return messageCode.toByteArray();
    }
}
