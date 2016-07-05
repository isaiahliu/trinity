package org.trinity.message.exception;

import org.trinity.message.GeneralMessageType;
import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;

/**
 * @author Isaiah Liu
 *
 *         Interface of all exception messages.
 */
public interface IExceptionMessage extends IMessage {
    @Override
    default IMessageType getMessageType() {
        return GeneralMessageType.ERRORMESSAGE;
    }
}
