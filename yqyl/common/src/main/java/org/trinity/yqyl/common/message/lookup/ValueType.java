package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public enum ValueType implements IMessage {
    STRING("S"),
    DATE("D"),
    TIME("T"),
    TIMESTAMP("DT"),
    NUMBER("N");

    private final String messageCode;

    private ValueType(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public IMessageType getMessageType() {
        return LookupType.VALUE_TYPE;
    }

}
