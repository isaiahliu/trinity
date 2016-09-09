package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum ServiceSupplierClientStatus implements ILookupMessage<LookupType> {
    ACTIVE("A"),
    DISABLED("D"),
    INACTIVE("I"),
    PROPOSAL("P"),
    AWAITING_PAYMENT("W");

    private final String messageCode;

    private ServiceSupplierClientStatus(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.SERVICE_SUPPLIER_CLIENT_STATUS;
    }

}
