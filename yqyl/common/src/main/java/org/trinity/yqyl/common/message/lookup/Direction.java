package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum Direction implements ILookupMessage<LookupType> {
    IN("I"),
    OUT("O");

    private final String messageCode;

    private Direction(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.DIRECTION;
    }

}
