package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum RoleName implements ILookupMessage<LookupType> {
    SUPER_USER("SUPER");

    private final String messageCode;

    private RoleName(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.ROLE_NAME;
    }

}
