package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum TransactionType implements ILookupMessage<LookupType> {
    POS("P"),
    ONLINE("O"),
    CASH("C");

    private final String messageCode;

    private TransactionType(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.TRANSACTION_TYPE;
    }

}
