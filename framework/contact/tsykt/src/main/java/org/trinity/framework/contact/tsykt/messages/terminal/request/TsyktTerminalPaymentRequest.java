package org.trinity.framework.contact.tsykt.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalPaymentRequest extends AbstractTsyktTerminalRequest {
    @ContactMessageField(fieldType = FieldType.VAR_BCD, length = 19, bitmapPos = 2, order = 2)
    private String account;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private String processCode;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private String serialCode;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private final String notUsed22 = "021";

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private final String notUsed25 = "00";

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private String terminalId;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private String receiver;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private String password;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private String notUsed60;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private final String transactionType = "01";

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private String notUsed60_2;

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private final String notUsed60_3 = "000";

    @ContactMessageField(fieldType = FieldType.BYTE, bitmapPos = 2, order = 2)
    private final String notUsed63 = "";

    @Override
    protected int getDefaultMessageId() {
        return 0x0200;
    }

    @Override
    protected int[] getMandatoryBitMapFlags() {
        return new int[] { 2, 3, 4, 11, 22, 25, 41, 42, 52, 60, 64 };
    }
}
