package org.trinity.framework.contact.jtt808.messages;

public abstract class AbstractJtt808Request extends AbstractJtt808Message {
    @Override
    public int getRequestSerialNumber() {
        return 0;
    }

    @Override
    public boolean isRequireResponse() {
        return true;
    }
}
