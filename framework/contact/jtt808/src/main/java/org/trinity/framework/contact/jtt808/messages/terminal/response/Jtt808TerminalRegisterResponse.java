package org.trinity.framework.contact.jtt808.messages.terminal.response;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.IContactMessageFieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808TerminalRegisterResponse extends AbstractJtt808TerminalResponse {
    public static enum TerminalRegisterResponseResult implements IContactMessageFieldType {
        SUCCESS(0),
        VEHICLE_REGISTERED(1),
        VEHICLE_NOT_FOUND(2),
        DEVICE_REGISTERED(3),
        DEVICE_NOT_FOUND(4);

        private final int value;

        private TerminalRegisterResponseResult(final int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    @ContactMessageField(fieldType = FieldType.WORD, order = -999)
    private int requestSerialNumber;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 1)
    private TerminalRegisterResponseResult result;

    @ContactMessageField(fieldType = FieldType.STRING, order = 2, length = -1)
    private String sessionId;

    @Override
    public int getRequestSerialNumber() {
        return requestSerialNumber;
    }

    public TerminalRegisterResponseResult getResult() {
        return result;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void setRequestSerialNumber(final int requestSerialNumber) {
        this.requestSerialNumber = requestSerialNumber;
    }

    public void setResult(final TerminalRegisterResponseResult result) {
        this.result = result;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x8100;
    }
}
