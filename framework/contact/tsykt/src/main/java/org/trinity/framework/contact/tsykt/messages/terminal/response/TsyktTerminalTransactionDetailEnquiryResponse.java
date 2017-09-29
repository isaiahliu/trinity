package org.trinity.framework.contact.tsykt.messages.terminal.response;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalTransactionDetailEnquiryResponse extends AbstractTsyktTerminalResponse {
    @ContactMessageField(fieldType = FieldType.VAR_BCD, length = 19, bitmapPos = 2, order = 1, padLetter = '0')
    private String account;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 3, order = 2, padLetter = '0')
    private String transactionCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 6, bitmapPos = 4, order = 3, padLetter = '0')
    private String amount;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 11, order = 4, padLetter = '0')
    private String serialNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 1, bitmapPos = 25, order = 5, padLetter = '0')
    private String serviceConditionCode = "00";

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 37, order = 6, padLetter = '0')
    private String referenceCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 2, bitmapPos = 39, order = 7, padLetter = '0')
    private String responseCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 41, order = 8, padLetter = '0')
    private String terminalCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 42, order = 9, padLetter = '0')
    private String shopCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 10, padLetter = '0')
    private int field60 = 11;

    @ContactMessageField(fieldType = FieldType.BCD, length = 1, bitmapPos = 60, order = 11, padLetter = '0')
    private int transactionTypeCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 60, order = 12, padLetter = '0')
    private int batchNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 13, padLetter = '0')
    private int manageNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 63, order = 14, padLetter = '0')
    private int field63 = 0;

    @ContactMessageField(fieldType = FieldType.UTF8, length = 30, bitmapPos = 63, order = 15)
    private String txShopName;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 4, bitmapPos = 63, order = 16)
    private String txType;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 14, bitmapPos = 63, order = 17)
    private String txDatetime;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 63, order = 18)
    private String txAmount;

    @ContactMessageField(fieldType = FieldType.MAC, bitmapPos = 64, order = 19)
    private String mac;

    public String getAccount() {
        return account;
    }

    public String getAmount() {
        return amount;
    }

    public int getBatchNo() {
        return batchNo;
    }

    public int getField60() {
        return field60;
    }

    public int getField63() {
        return field63;
    }

    public String getMac() {
        return mac;
    }

    public int getManageNo() {
        return manageNo;
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

    public String getServiceConditionCode() {
        return serviceConditionCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public int getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public String getTxAmount() {
        return txAmount;
    }

    public String getTxDatetime() {
        return txDatetime;
    }

    public String getTxShopName() {
        return txShopName;
    }

    public String getTxType() {
        return txType;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
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

    public void setMac(final String mac) {
        this.mac = mac;
    }

    public void setManageNo(final int manageNo) {
        this.manageNo = manageNo;
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

    public void setServiceConditionCode(final String serviceConditionCode) {
        this.serviceConditionCode = serviceConditionCode;
    }

    public void setShopCode(final String shopCode) {
        this.shopCode = shopCode;
    }

    public void setTerminalCode(final String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public void setTransactionCode(final String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public void setTransactionTypeCode(final int transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public void setTxAmount(final String txAmount) {
        this.txAmount = txAmount;
    }

    public void setTxDatetime(final String txDatetime) {
        this.txDatetime = txDatetime;
    }

    public void setTxShopName(final String txShopName) {
        this.txShopName = txShopName;
    }

    public void setTxType(final String txType) {
        this.txType = txType;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x7106;
    }

    @Override
    protected int[] getMandatoryBitMapFlags() {
        return new int[] { 2, 3, 4, 11, 25, 37, 41, 42, 60, 63 };
    }
}
