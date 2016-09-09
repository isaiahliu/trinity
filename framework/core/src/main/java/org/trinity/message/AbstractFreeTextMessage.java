package org.trinity.message;

/**
 * This type of message can have specified message code.
 * 
 * @author Isaiah Liu
 *
 * @see IMessage
 */
public abstract class AbstractFreeTextMessage<TType extends IMessageType> implements IMessage<TType> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String messageCode;

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public void setMessageCode(final String messageCode) {
        this.messageCode = messageCode;
    }
}
