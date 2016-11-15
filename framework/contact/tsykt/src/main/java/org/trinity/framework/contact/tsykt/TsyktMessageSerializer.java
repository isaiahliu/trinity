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
        attribute |= header.getBodyLength();

        ContactMessageUtil.write(messageCode, attribute, 2, StoreMethod.BIG_END);

        ContactMessageUtil.write(messageCode, header.getSerialNumber(), 2, StoreMethod.BIG_END);

        if (hasSubPackage) {
            ContactMessageUtil.write(messageCode, header.getPackageCount(), 2, StoreMethod.BIG_END);

            ContactMessageUtil.write(messageCode, header.getPackageIndex(), 2, StoreMethod.BIG_END);
        }

        return messageCode.toByteArray();
    }
}
