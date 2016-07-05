package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public enum RecordStatus implements IMessage {
    ACTIVE("A"),
    DISABLED("D");

    private final String messageCode;

    private RecordStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public IMessageType getMessageType() {
        return LookupType.RECORD_STATUS;
    }

}
