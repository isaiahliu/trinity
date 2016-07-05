package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public enum SystemAttributeKey implements IMessage {
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
    public IMessageType getMessageType() {
        return LookupType.SYSTEM_ATTRIBUTE_KEY;
    }

}
