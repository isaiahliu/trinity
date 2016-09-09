package org.trinity.message;

import java.io.Serializable;

/**
 * The super interface of all message types. The message has a message type and message code which can lead to a message
 * key.
 * 
 * @author Isaiah Liu
 */
public interface IMessage<TType extends IMessageType> extends Serializable {
    /**
     * The message code
     *
     * @return message code string
     */
    String getMessageCode();

    /**
     * Get the message key joined by message type and message code.
     *
     * @return message key
     * @see IMessageType
     */
    default String getMessageCodeWithPrefix() {
        return getMessageType().getMessageTypePrefix() + getMessageCode();
    }

    /**
     * The type of the message
     *
     * @return message type
     * @see IMessageType
     */
    TType getMessageType();

    /**
     * For some message instance, the message code can be specified. This method will do nothing as default.
     *
     * @param messageCode
     */
    default void setMessageCode(final String messageCode) {
    }
}
