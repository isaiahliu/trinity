package org.trinity.framework.contact.tsykt.messages;

public abstract class AbstractTsyktResponse extends AbstractTsyktMessage {
    @Override
    public abstract int getRequestSerialNumber();

    @Override
    public boolean isRequireResponse() {
        return false;
    }

    public abstract void setRequestSerialNumber(int requestSerialNumber);
}
