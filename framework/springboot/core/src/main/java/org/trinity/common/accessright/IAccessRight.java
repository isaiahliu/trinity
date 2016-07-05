package org.trinity.common.accessright;

import org.trinity.message.GeneralMessageType;
import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;

/**
 * @author Isaiah Liu
 *
 *         The interface of all accessright message.
 *
 * @see IMessage
 */
public interface IAccessRight extends IMessage {
    @Override
    default IMessageType getMessageType() {
        return GeneralMessageType.ACCESSRIGHT;
    }
}
