package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum OrderOperation implements ILookupMessage<LookupType> {
    PROPOSAL("10"),
    TAKEN("20"),
    ASSIGNMENT("30"),
    PROCESSING("40"),
    PAYED("50"),
    RECEIPT_UPLOADED("55"),
    APPRAISED("60"),
    SETTLED("100"),
    CANCELLED("110");

    private final String messageCode;

    private OrderOperation(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.ORDER_OPERATION;
    }

}
