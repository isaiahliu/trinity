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

        ContactMessageUtil.write(messageCode, header.getId(), 2, StoreMethod.BIG_END);

        final boolean hasSubPackage = (header.getPackageCount() > 1);
        int attribute = hasSubPackage ? 1 : 0;
        attribute <<= 3;
        attribute |= header.getEncryptionMode();
        attribute <<= 10;
        attribute |= header.getBodyLength();

        ContactMessageUtil.write(messageCode, attribute, 2, StoreMethod.BIG_END);

        final StringBuilder phoneNo = new StringBuilder(header.getPhoneNo());

        while (phoneNo.length() < 12) {
            phoneNo.insert(0, '0');
        }

        String str = phoneNo.toString();

        for (int i = 0; i < 12; i = i + 2) {
            final byte temp = (byte) (int) Integer.valueOf(str.substring(0, 2), 16);

            messageCode.write(temp);

            str = str.substring(2);
        }

        ContactMessageUtil.write(messageCode, header.getSerialNumber(), 2, StoreMethod.BIG_END);

        if (hasSubPackage) {
            ContactMessageUtil.write(messageCode, header.getPackageCount(), 2, StoreMethod.BIG_END);

            ContactMessageUtil.write(messageCode, header.getPackageIndex(), 2, StoreMethod.BIG_END);
        }

        return messageCode.toByteArray();
    }
}
