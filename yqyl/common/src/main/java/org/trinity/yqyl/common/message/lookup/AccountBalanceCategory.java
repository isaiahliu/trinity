package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum AccountBalanceCategory implements ILookupMessage<LookupType> {
    ACTIVE("A");

    private final String messageCode;

    private AccountBalanceCategory(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.ACCOUNT_BALANCE_CATEGORY;
    }

}
