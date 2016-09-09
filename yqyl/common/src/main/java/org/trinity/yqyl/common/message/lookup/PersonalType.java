package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum PersonalType implements ILookupMessage<LookupType> {
    PERSONAL("P"),
    BUSINESS("B");

    private final String messageCode;

    private PersonalType(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.PERSONAL_TYPE;
    }

}
