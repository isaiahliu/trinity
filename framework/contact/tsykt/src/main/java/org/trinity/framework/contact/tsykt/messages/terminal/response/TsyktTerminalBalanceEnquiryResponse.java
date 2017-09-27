package org.trinity.framework.contact.tsykt.messages.terminal.response;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalBalanceEnquiryResponse extends AbstractTsyktTerminalResponse {
    @ContactMessageField(fieldType = FieldType.VAR_BCD, length = 19, bitmapPos = 2, order = 1, padLetter = '0')
    private String account;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 3, order = 2, padLetter = '0')
    private String transactionCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 6, bitmapPos = 4, order = 3, padLetter = '0')
    private String paymentAmount;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 11, order = 4, padLetter = '0')
    private String serialNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 12, order = 5, padLetter = '0')
    private String time;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 13, order = 6, padLetter = '0')
    private String date;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 15, order = 7, padLetter = '0')
    private String settlementDate;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 16, order = 8, padLetter = '0')
    private String settlementYear;

    @ContactMessageField(fieldType = FieldType.BCD, length = 1, bitmapPos = 25, order = 9, padLetter = '0')
    private String serviceConditionCode = "00";

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 26, order = 10, padLetter = '0')
    private String accountType;

    @ContactMessageField(fieldType = FieldType.VAR_BCD, length = 11, bitmapPos = 32, order = 11, padLetter = '0')
    private String queryCode;

    @ContactMessageField(fieldType = FieldType.VAR_BCD, length = 11, bitmapPos = 33, order = 12, padLetter = '0')
    private String settlementCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 37, order = 13, padLetter = '0')
    private String queryRefCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 2, bitmapPos = 39, order = 14, padLetter = '0')
    private String responseCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 41, order = 15, padLetter = '0')
    private String terminalCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 42, order = 16, padLetter = '0')
    private String shopCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 8, bitmapPos = 52, order = 17)
    private String password;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 54, order = 18, padLetter = '0')
    private String amountLength;

    @ContactMessageField(fieldType = FieldType.NBYTE, getLengthFrom = "amountLength", bitmapPos = 54, order = 19)
    private String amount;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 20, padLetter = '0')
    private int field60 = 11;

    @ContactMessageField(fieldType = FieldType.BCD, length = 1, bitmapPos = 60, order = 21, padLetter = '0')
    private String transactionTypeCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 60, order = 22, padLetter = '0')
    private String batchNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 60, order = 23, padLetter = '0')
    private String manageNo;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 63, order = 24, padLetter = '0')
    private int field63 = 63;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 16, bitmapPos = 63, order = 26)
    private String operatorCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 20, bitmapPos = 63, order = 26)
    private String accountCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 8, bitmapPos = 64, order = 27)
    private String mac;

    public String getAccount() {
        return account;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAmount() {
        return amount;
    }

    public String getAmountLength() {
        return amountLength;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public String getDate() {
        return date;
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

    public String getManageNo() {
        return manageNo;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public String getPassword() {
        return password;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getQueryCode() {
        return queryCode;
    }

    public String getQueryRefCode() {
        return queryRefCode;
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

    public String getSettlementCode() {
        return settlementCode;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public String getSettlementYear() {
        return settlementYear;
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

    public String getTransactionCode() {
        return transactionCode;
    }

    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public void setAccountCode(final String accountCode) {
        this.accountCode = accountCode;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public void setAmountLength(final String amountLength) {
        this.amountLength = amountLength;
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

    public void setField63(final int field63) {
        this.field63 = field63;
    }

    public void setMac(final String mac) {
        this.mac = mac;
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

    public void setPaymentAmount(final String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setQueryCode(final String queryCode) {
        this.queryCode = queryCode;
    }

    public void setQueryRefCode(final String queryRefCode) {
        this.queryRefCode = queryRefCode;
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

    public void setSettlementCode(final String settlementCode) {
        this.settlementCode = settlementCode;
    }

    public void setSettlementDate(final String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public void setSettlementYear(final String settlementYear) {
        this.settlementYear = settlementYear;
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

    public void setTransactionCode(final String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public void setTransactionTypeCode(final String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0210;
    }

    @Override
    protected int[] getMandatoryBitMapFlags() {
        return new int[] { 2, 3, 11, 12, 13, 15, 16, 25, 32, 37, 39, 41, 42, 52, 60, 63 };
    }
}
