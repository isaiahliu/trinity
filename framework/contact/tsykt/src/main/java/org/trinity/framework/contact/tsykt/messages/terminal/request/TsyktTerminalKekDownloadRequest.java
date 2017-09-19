package org.trinity.framework.contact.tsykt.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalKekDownloadRequest extends AbstractTsyktTerminalRequest {
    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 41, order = 2)
    private String terminalCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 42, order = 3)
    private String shopCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 4, padLetter = '0')
    private int field60 = 11;

    @ContactMessageField(fieldType = FieldType.BCD, length = 1, bitmapPos = 60, order = 5, padLetter = '0')
    private int transactionTypeCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 60, order = 6, padLetter = '0')
    private int batchNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 7, padLetter = '0')
    private int manageNo;

    public int getBatchNo() {
        return batchNo;
    }

    public int getField60() {
        return field60;
    }

    public int getManageNo() {
        return manageNo;
    }

    public String getShopCode() {
        return shopCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public int getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setBatchNo(final int batchNo) {
        this.batchNo = batchNo;
    }

    public void setField60(final int field60) {
        this.field60 = field60;
    }

    public void setManageNo(final int manageNo) {
        this.manageNo = manageNo;
    }

    public void setShopCode(final String shopCode) {
        this.shopCode = shopCode;
    }

    public void setTerminalCode(final String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public void setTransactionTypeCode(final int transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0800;
    }

    @Override
    protected int[] getMandatoryBitMapFlags() {
        return new int[] { 41, 42, 60 };
    }
}
