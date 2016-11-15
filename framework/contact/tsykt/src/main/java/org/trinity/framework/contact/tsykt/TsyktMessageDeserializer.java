package org.trinity.framework.contact.tsykt;

import java.io.ByteArrayInputStream;

import org.trinity.framework.contact.AbstractContactMessageDeserializer;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageUtil;

public final class TsyktMessageDeserializer extends AbstractContactMessageDeserializer<ITsyktMessageMeta, ITsyktMessage>
        implements ITsyktMessageDeserializer {
    private ITsyktMessageInstantiator messageInstantiator;

    @Override
    public ITsyktMessage deserializeBody(final ITsyktMessageMeta header, final ByteArrayInputStream messageCodes) {
        ITsyktMessage message = null;
        try {
            message = getMessageInstantiator().createMessage(header.getId());

            deserializeObject(message, messageCodes);
        } catch (final Exception e) {

        } finally {
            if (message != null) {
                message.setMeta(header);
            }
        }
        return message;
    }

    @Override
    public ITsyktMessageMeta deserializeHeader(final ByteArrayInputStream messageCodes) {
        final int id = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);
        final ITsyktMessageMeta header = new TsyktMessageMeta(id);

        int attribute = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);

        final int bodyLength = attribute & 0x3ff;

        header.setBodyLength(bodyLength);

        attribute >>= 10;

        attribute >>= 3;

        final boolean hasSubPackages = (attribute & 0x1) == 1;

        final StringBuilder phoneNo = new StringBuilder();

        boolean ignoreZero = true;
        for (int index = 4; index < 10; index++) {
            final int temp = ContactMessageUtil.read(messageCodes, 1, StoreMethod.BIG_END);

            if (ignoreZero && temp == 0) {
                continue;
            }

            String tempStr = "";

            tempStr = Integer.toHexString(temp);
            if (ignoreZero) {
                ignoreZero = false;
            } else if (tempStr.length() == 1) {
                phoneNo.append("0");
            }

            phoneNo.append(tempStr);
        }

        final int serialNumber = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);
        header.setSerialNumber(serialNumber);

        if (hasSubPackages) {
            final int totalPackageCount = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);
            final int packageIndex = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);

            header.setPackageCount(totalPackageCount);
            header.setPackageIndex(packageIndex);
        } else {
            header.setPackageCount(1);
            header.setPackageIndex(1);
        }
        return header;
    }

    public ITsyktMessageInstantiator getMessageInstantiator() {
        if (messageInstantiator == null) {
            messageInstantiator = new TsyktMessageInstantiator();
        }
        return messageInstantiator;
    }
}
