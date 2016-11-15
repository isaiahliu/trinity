package org.trinity.framework.contact.jtt808.messages;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public final class Jtt808SubPackageMessage extends AbstractJtt808Message {
    private final boolean requireResponse;

    public Jtt808SubPackageMessage(final boolean requireResponse) {
        this.requireResponse = requireResponse;
    }

    @Override
    public int getRequestSerialNumber() {
        return 0;
    }

    @Override
    public boolean isRequireResponse() {
        return requireResponse;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0;
    }
}
