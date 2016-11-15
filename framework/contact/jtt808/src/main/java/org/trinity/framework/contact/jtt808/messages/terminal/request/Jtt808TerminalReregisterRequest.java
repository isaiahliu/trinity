package org.trinity.framework.contact.jtt808.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808TerminalReregisterRequest extends AbstractJtt808TerminalRequest {
    @ContactMessageField(fieldType = FieldType.STRING, length = -1, order = 1)
    private String registrationCode;

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(final String registrationCode) {
        this.registrationCode = registrationCode;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0102;
    }

}
