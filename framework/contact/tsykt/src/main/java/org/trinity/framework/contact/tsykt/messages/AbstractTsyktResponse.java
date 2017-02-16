package org.trinity.framework.contact.tsykt.messages;

public abstract class AbstractTsyktResponse extends AbstractTsyktMessage {
    @Override
    public int getRequestSerialNumber() {
        return 0;
    }

    @Override
    public boolean isRequireResponse() {
        return false;
    }
}
