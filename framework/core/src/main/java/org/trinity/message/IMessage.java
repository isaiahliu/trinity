package org.trinity.message;

import java.io.Serializable;

/**
 * @author Isaiah Liu
 *
 *         The super interface of all message types. The message has a message type and message code which can lead to a
 *         message key.
 */
public interface IMessage extends Serializable {
    /**
     * The type of the message
     *
     * @return message type
     * @see IMessageType
     */
    IMessageType getMessageType();

    /**
     * The message code
     *
     * @return message code string
     */
    String getMessageCode();

    /**
     * For some message instance, the message code can be specified. This method will do nothing as default.
     *
     * @param messageCode
     */
    default void setMessageCode(final String messageCode) {
    }

    /**
     * Get the message key joined by message type and message code.
     *
     * @return message key
     * @see IMessageType
     */
    default String getMessageCodeWithPrefix() {
        return getMessageType().getMessageTypePrefix() + getMessageCode();
    }
}
