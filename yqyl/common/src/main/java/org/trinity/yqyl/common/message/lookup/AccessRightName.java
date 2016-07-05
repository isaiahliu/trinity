package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.AbstractFreeTextMessage;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public class AccessRightName extends AbstractFreeTextMessage {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public IMessageType getMessageType() {
        return LookupType.ACCESS_RIGHT_NAME;
    }
}
