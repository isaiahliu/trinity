package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum ServiceOrderRequirementStatus implements ILookupMessage<LookupType> {
    ACTIVE("A"),
    DISABLED("D");

    private final String messageCode;

    private ServiceOrderRequirementStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.SERVICE_ORDER_REQUIREMENT_STATUS;
    }

}
