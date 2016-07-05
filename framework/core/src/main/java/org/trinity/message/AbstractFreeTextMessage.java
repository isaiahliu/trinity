package org.trinity.message;

/**
 * @author Isaiah Liu
 *
 *         This type of message can have specified message code.
 * 
 * @see IMessage
 */
public abstract class AbstractFreeTextMessage implements IMessage {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String messageCode;

    @Override
    public void setMessageCode(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }
}
