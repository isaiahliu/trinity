package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.tsykt.messages.terminal.response.TsyktTerminalSignUpResponse;

public final class TsyktMessageInstantiator implements ITsyktMessageInstantiator {
    @Override
    public ITsyktMessage createMessage(final int messageId) {
        switch (messageId) {
        case 0x0810:
            return new TsyktTerminalSignUpResponse();
        default:
            return null;
        }
    }
}
