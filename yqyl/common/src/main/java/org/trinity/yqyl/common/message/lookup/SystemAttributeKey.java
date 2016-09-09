package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum SystemAttributeKey implements ILookupMessage<LookupType> {
    TOKEN_EXPIRE_DAYS("TKEXPDAYS");

    private final String messageCode;

    private SystemAttributeKey(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.SYSTEM_ATTRIBUTE_KEY;
    }

}
