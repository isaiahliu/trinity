package org.trinity.framework.contact.jtt808.messages.platform.response;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.IContactMessageFieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808PlatformGeneralResponse extends AbstractJtt808PlatformResponse {
    public static enum PlatformGeneralResponseResult implements IContactMessageFieldType {
        SUCCESS(0),
        FAILED(1),
        BAD_REQUEST(2),
        UNSUPPORTED(3);

        private final int value;

        private PlatformGeneralResponseResult(final int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    @ContactMessageField(fieldType = FieldType.WORD, order = 1)
    private int requestSerialNumber;

    @ContactMessageField(fieldType = FieldType.WORD, order = 2)
    private int requestMessageId;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 3)
    private PlatformGeneralResponseResult result;

    public int getRequestMessageId() {
        return requestMessageId;
    }

    @Override
    public int getRequestSerialNumber() {
        return requestSerialNumber;
    }

    public PlatformGeneralResponseResult getResult() {
        return result;
    }

    public void setRequestMessageId(final int requestMessageId) {
        this.requestMessageId = requestMessageId;
    }

    @Override
    public void setRequestSerialNumber(final int requestSerialNumber) {
        this.requestSerialNumber = requestSerialNumber;

    }

    public void setResult(final PlatformGeneralResponseResult result) {
        this.result = result;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0001;
    }

}
