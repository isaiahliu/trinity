package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum Currency implements ILookupMessage<LookupType> {
    CASH("C"),
    YIQUAN("Y");

    private final String messageCode;

    private Currency(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.CURRENCY;
    }

}
