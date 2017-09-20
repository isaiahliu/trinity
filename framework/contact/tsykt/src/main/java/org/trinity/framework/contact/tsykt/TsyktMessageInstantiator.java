package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.tsykt.messages.terminal.response.TsyktTerminalBalanceEnquiryResponse;
import org.trinity.framework.contact.tsykt.messages.terminal.response.TsyktTerminalSignUpResponse;
import org.trinity.framework.contact.tsykt.messages.terminal.response.TsyktTerminalTransactionEnquiryResponse;

public final class TsyktMessageInstantiator implements ITsyktMessageInstantiator {
    @Override
    public ITsyktMessage createMessage(final int messageId) {
        switch (messageId) {
        case 0x0810:
            return new TsyktTerminalSignUpResponse();
        case 0x0210:
            return new TsyktTerminalBalanceEnquiryResponse();
        case 0x7096:
            return new TsyktTerminalTransactionEnquiryResponse();
        default:
            return null;
        }
    }
}
