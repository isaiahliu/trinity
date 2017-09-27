package org.trinity.framework.contact.tsykt.messages.terminal.response;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalSignUpResponse extends AbstractTsyktTerminalResponse {
    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 11, order = 1, padLetter = '0')
    private String serialNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 12, order = 2, padLetter = '0')
    private String time;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 13, order = 3, padLetter = '0')
    private String date;

    @ContactMessageField(fieldType = FieldType.VAR_BCD, bitmapPos = 32, order = 4, padLetter = '0')
    private String platformCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 37, order = 5)
    private String referenceCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 2, bitmapPos = 39, order = 6)
    private String responseCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 41, order = 7)
    private String terminalCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 42, order = 8)
    private String shopCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 9, padLetter = '0')
    private int field60 = 11;

    @ContactMessageField(fieldType = FieldType.BCD, length = 1, bitmapPos = 60, order = 10, padLetter = '0')
    private String transactionTypeCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 60, order = 11, padLetter = '0')
    private String batchNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 12, padLetter = '0')
    private String manageNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 62, order = 14, padLetter = '0')
    private int field62 = 48;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 62, order = 15)
    private String pik;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 8, bitmapPos = 62, order = 16)
    private String pikCheckValue;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 62, order = 17)
    private String mak;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 8, bitmapPos = 62, order = 18)
    private String makCheckValue;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 19, padLetter = '0')
    private int field63 = 33;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 63, order = 22)
    private String operatorCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 1, bitmapPos = 63, order = 23)
    private String passwordVerifyCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 63, order = 24)
    private String password;

    public String getBatchNo() {
        return batchNo;
    }

    public String getDate() {
        return date;
    }

    public int getField60() {
        return field60;
    }

    public int getField62() {
        return field62;
    }

    public int getField63() {
        return field63;
    }

    public String getMak() {
        return mak;
    }

    public String getMakCheckValue() {
        return makCheckValue;
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

    public String getPik() {
        return pik;
    }

    public String getPikCheckValue() {
        return pikCheckValue;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    @Override
    public String getResponseCode() {
        return responseCode;
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

    public String getTime() {
        return time;
    }

    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setBatchNo(final String batchNo) {
        this.batchNo = batchNo;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public void setField60(final int field60) {
        this.field60 = field60;
    }

    public void setField62(final int field62) {
        this.field62 = field62;
    }

    public void setField63(final int field63) {
        this.field63 = field63;
    }

    public void setMak(final String mak) {
        this.mak = mak;
    }

    public void setMakCheckValue(final String makCheckValue) {
        this.makCheckValue = makCheckValue;
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

    public void setPik(final String pik) {
        this.pik = pik;
    }

    public void setPikCheckValue(final String pikCheckValue) {
        this.pikCheckValue = pikCheckValue;
    }

    public void setPlatformCode(final String platformCode) {
        this.platformCode = platformCode;
    }

    public void setReferenceCode(final String referenceCode) {
        this.referenceCode = referenceCode;
    }

    @Override
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
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

    public void setTime(final String time) {
        this.time = time;
    }

    public void setTransactionTypeCode(final String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0810;
    }

    @Override
    protected int[] getMandatoryBitMapFlags() {
        return new int[] { 11, 12, 13, 32, 37, 39, 41, 42, 60 };
    }
}
