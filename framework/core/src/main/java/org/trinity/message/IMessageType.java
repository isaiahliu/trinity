package org.trinity.message;

import java.io.Serializable;

/**
 * Message type interface
 *
 * @author Isaiah Liu
 */
public interface IMessageType extends Serializable {
    String SPLITTER = ".";

    /**
     * The name of the message type.
     *
     * @return Message type name.
     */
    String getMessageTypeName();

    /**
     * Get the full message type name as the message prefix.
     *
     * E.g. Message Type "button" has a parent type "caption". A message "commit" is a "button" message.
     *
     * Message type can get a prefix "caption_button_" for the message so that the message can get the full message key
     * "caption_button_commit
     *
     * @return Full message type name.
     */
    default String getMessageTypePrefix() {
        return getParentType().getMessageTypePrefix() + getMessageTypeName() + SPLITTER;
    }

    /**
     * The parent type of this type.
     *
     * @return Parent type
     */
    IMessageType getParentType();

}
