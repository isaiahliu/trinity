package org.trinity.framework.contact.tsykt.messages.terminal.response;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktPlatformGeneralResponse extends AbstractTsyktPlatformResponse {
    @Override
    protected int getDefaultMessageId() {
        return 0x0210;
    }

    @Override
    protected int[] getMandatoryBitMapFlags() {
        return new int[] { 2, 3, 11, 12, 13, 15, 16, 25, 32, 37, 39, 41, 42, 52, 60, 63 };
    }

}
