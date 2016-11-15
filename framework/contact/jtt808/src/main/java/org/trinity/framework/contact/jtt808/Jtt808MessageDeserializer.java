package org.trinity.framework.contact.jtt808;

import java.io.ByteArrayInputStream;

import org.trinity.framework.contact.AbstractContactMessageDeserializer;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageUtil;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalUnIdentifiedRequest;

public final class Jtt808MessageDeserializer extends AbstractContactMessageDeserializer<IJtt808MessageMeta, IJtt808Message>
        implements IJtt808MessageDeserializer {
    private IJtt808MessageInstantiator messageInstantiator;

    @Override
    public IJtt808Message deserializeBody(final IJtt808MessageMeta header, final ByteArrayInputStream messageCodes) {
        IJtt808Message message = null;
        try {
            message = getMessageInstantiator().createMessage(header.getId());

            if (message instanceof Jtt808TerminalUnIdentifiedRequest) {
                return message;
            }

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
    public IJtt808MessageMeta deserializeHeader(final ByteArrayInputStream messageCodes) {
        final int id = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);
        final IJtt808MessageMeta header = new Jtt808MessageMeta(id);

        int attribute = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);

        final int bodyLength = attribute & 0x3ff;

        header.setBodyLength(bodyLength);

        attribute >>= 10;

        header.setEncryptionMode(attribute & 0x7);

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

        header.setPhoneNo(phoneNo.toString());

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

    public IJtt808MessageInstantiator getMessageInstantiator() {
        if (messageInstantiator == null) {
            messageInstantiator = new Jtt808MessageInstantiator();
        }
        return messageInstantiator;
    }
}
