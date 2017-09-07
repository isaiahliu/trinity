package org.trinity.framework.contact.tsykt.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalSignUpRequest extends AbstractTsyktTerminalRequest {
    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 11, order = 1, padLetter = '0')
    private int serialNo;

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

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 8, padLetter = '0')
    private int field63 = 33;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 63, order = 9)
    private String operatorCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 1, bitmapPos = 63, order = 10)
    private String passwordVerifyCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 63, order = 11)
    private String password;

    public int getBatchNo() {
        return batchNo;
    }

    public int getField60() {
        return field60;
    }

    public int getField63() {
        return field63;
    }

    public int getManageNo() {
        return manageNo;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordVerifyCode() {
        return passwordVerifyCode;
    }

    public int getSerialNo() {
        return serialNo;
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

    public void setField63(final int field63) {
        this.field63 = field63;
    }

    public void setManageNo(final int manageNo) {
        this.manageNo = manageNo;
    }

    public void setOperatorCode(final String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setPasswordVerifyCode(final String passwordVerifyCode) {
        this.passwordVerifyCode = passwordVerifyCode;
    }

    public void setSerialNo(final int serialNo) {
        this.serialNo = serialNo;
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
        return new int[] { 11, 41, 42, 60, 63 };
    }
}
