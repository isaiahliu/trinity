package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.tsykt.messages.terminal.response.TsyktTerminalBalanceEnquiryResponse;
import org.trinity.framework.contact.tsykt.messages.terminal.response.TsyktTerminalSignUpResponse;

public final class TsyktMessageInstantiator implements ITsyktMessageInstantiator {
    @Override
    public ITsyktMessage createMessage(final int messageId) {
        switch (messageId) {
        case 0x0810:
            return new TsyktTerminalSignUpResponse();
        case 0x0210:
            return new TsyktTerminalBalanceEnquiryResponse();
        default:
            return null;
        }
    }
}
