package org.trinity.framework.contact.jtt808.messages.platform.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808PlatformUploadDriverRequest extends AbstractJtt808PlatformRequest {
    @Override
    public boolean isRequireResponse() {
        return false;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x8702;
    }
}
