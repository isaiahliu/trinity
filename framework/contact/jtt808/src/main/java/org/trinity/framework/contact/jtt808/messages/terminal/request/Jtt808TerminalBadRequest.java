package org.trinity.framework.contact.jtt808.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808TerminalBadRequest extends AbstractJtt808TerminalRequest {
    @Override
    protected int getDefaultMessageId() {
        return 0;
    }
}
