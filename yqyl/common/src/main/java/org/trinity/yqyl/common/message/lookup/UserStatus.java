package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public enum UserStatus implements IMessage {
    UNREGISTERED("U"),
    ACTIVE("A"),
    DESTROYED("D");

    private final String messageCode;

    private UserStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public IMessageType getMessageType() {
        return LookupType.USER_STATUS;
    }

}
