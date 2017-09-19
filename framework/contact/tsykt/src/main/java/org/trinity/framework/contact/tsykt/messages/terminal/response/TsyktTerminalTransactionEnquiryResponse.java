package org.trinity.framework.contact.tsykt.messages.terminal.response;

import java.util.List;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktTerminalTransactionEnquiryResponse extends AbstractTsyktPlatformResponse {
    public static class Transaction {
        @ContactMessageField(fieldType = FieldType.BCD, length = 7, bitmapPos = 63, order = 1)
        private String datetime;

        @ContactMessageField(fieldType = FieldType.NBYTE, length = 20, bitmapPos = 63, order = 2)
        private String shopName;

        @ContactMessageField(fieldType = FieldType.BCD, length = 6, bitmapPos = 63, order = 3)
        private String amount;

        @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 63, order = 4)
        private String type;

        public String getAmount() {
            return amount;
        }

        public String getDatetime() {
            return datetime;
        }

        public String getShopName() {
            return shopName;
        }

        public String getType() {
            return type;
        }

        public void setAmount(final String amount) {
            this.amount = amount;
        }

        public void setDatetime(final String datetime) {
            this.datetime = datetime;
        }

        public void setShopName(final String shopName) {
            this.shopName = shopName;
        }

        public void setType(final String type) {
            this.type = type;
        }
    }

    @ContactMessageField(fieldType = FieldType.VAR_BCD, length = 19, bitmapPos = 2, order = 1, padLetter = '0')
    private String account;

    @ContactMessageField(fieldType = FieldType.BCD, length = 3, bitmapPos = 3, order = 2, padLetter = '0')
    private String transactionCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 6, bitmapPos = 4, order = 3, padLetter = '0')
    private String amount;

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
    private final String serviceConditionCode = "00";

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 37, order = 10, padLetter = '0')
    private String queryRefCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 2, bitmapPos = 39, order = 11, padLetter = '0')
    private String responseCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 41, order = 12, padLetter = '0')
    private String terminalCode;

    @ContactMessageField(fieldType = FieldType.NBYTE, length = 12, bitmapPos = 42, order = 13, padLetter = '0')
    private String shopCode;

    @ContactMessageField(fieldType = FieldType.BCD, length = 2, bitmapPos = 63, order = 14, padLetter = '0')
    private final int field63 = 24;

    @ContactMessageField(fieldType = FieldType.BCD, length = 4, bitmapPos = 63, order = 15, padLetter = '0')
    private String startDate;

    @ContactMessageField(fieldType = FieldType.BCD, length = 4, bitmapPos = 63, order = 16, padLetter = '0')
    private String endDate;

    @ContactMessageField(fieldType = FieldType.BCD, length = 4, bitmapPos = 63, order = 17, padLetter = '0')
    private int startIndex;

    @ContactMessageField(fieldType = FieldType.BCD, length = 4, bitmapPos = 63, order = 18, padLetter = '0')
    private int totalSize;

    @ContactMessageField(fieldType = FieldType.BCD, length = 4, bitmapPos = 63, order = 19, padLetter = '0')
    private int currentSize;

    @ContactMessageField(fieldType = FieldType.COMPONENT_LIST, bitmapPos = 63, order = 20, getLengthFrom = "currentSize")
    private List<Transaction> transaction;

    @ContactMessageField(fieldType = FieldType.MAC, bitmapPos = 64, order = 21)
    private String mac;

    @Override
    protected int getDefaultMessageId() {
        return 0x7096;
    }

    @Override
    protected int[] getMandatoryBitMapFlags() {
        return new int[] { 2, 3, 4, 11, 12, 13, 15, 16, 25, 39, 41, 42, 63 };
    }
}
