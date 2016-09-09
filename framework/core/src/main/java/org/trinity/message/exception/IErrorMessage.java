package org.trinity.message.exception;

import org.trinity.message.GeneralMessageType;
import org.trinity.message.IMessage;

/**
 * Interface of all exception messages.
 * 
 * @author Isaiah Liu
 */
public interface IErrorMessage extends IMessage<GeneralMessageType> {
    @Override
    default GeneralMessageType getMessageType() {
        return GeneralMessageType.ERRORMESSAGE;
    }
}
