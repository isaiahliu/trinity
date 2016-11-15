package org.trinity.framework.contact.jtt808.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808Terminal1505Request extends AbstractJtt808TerminalRequest {
    @ContactMessageField(fieldType = FieldType.BYTEARRAY, length = -1, order = 1)
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x1505;
    }
}
