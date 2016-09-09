package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum ClientStatus implements ILookupMessage<LookupType> {
    ACTIVE("A"),
    YIQUAN("Y");

    private final String messageCode;

    private ClientStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.CLIENT_STATUS;
    }

}
