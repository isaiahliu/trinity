package org.trinity.framework.contact.tsykt.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalSignUpRequest extends AbstractTsyktTerminalRequest {
    @ContactMessageField(fieldType = FieldType.VAR_BCD, length = 6, bitmapPos = 11, order = 1)
    private String serialNo;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 41, order = 2)
    private String terminalCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 42, order = 3)
    private String shopCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 2, bitmapPos = 60, order = 4)
    private String transactionTypeCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 6, bitmapPos = 60, order = 5)
    private String batchNo;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 3, bitmapPos = 60, order = 6)
    private String manageNo;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 63, order = 7)
    private String operatorCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 1, bitmapPos = 63, order = 8)
    private String passwordVerifyCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 63, order = 9)
    private String password;

    public String getBatchNo() {
        return batchNo;
    }

    public String getManageNo() {
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

    public String getSerialNo() {
        return serialNo;
    }

    public String getShopCode() {
        return shopCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setBatchNo(final String batchNo) {
        this.batchNo = batchNo;
    }

    public void setManageNo(final String manageNo) {
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

    public void setSerialNo(final String serialNo) {
        this.serialNo = serialNo;
    }

    public void setShopCode(final String shopCode) {
        this.shopCode = shopCode;
    }

    public void setTerminalCode(final String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public void setTransactionTypeCode(final String transactionTypeCode) {
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
