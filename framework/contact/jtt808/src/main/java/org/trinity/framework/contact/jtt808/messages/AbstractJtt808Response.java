package org.trinity.framework.contact.jtt808.messages;

public abstract class AbstractJtt808Response extends AbstractJtt808Message {
    @Override
    public abstract int getRequestSerialNumber();

    @Override
    public boolean isRequireResponse() {
        return false;
    }

    public abstract void setRequestSerialNumber(int requestSerialNumber);
}
