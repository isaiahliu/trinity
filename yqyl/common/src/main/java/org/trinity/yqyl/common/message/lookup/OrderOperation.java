package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum OrderOperation implements ILookupMessage<LookupType> {
    PROPOSAL("1");

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
