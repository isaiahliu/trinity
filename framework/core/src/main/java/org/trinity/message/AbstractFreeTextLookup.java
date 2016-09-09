package org.trinity.message;

/**
 * This type of message can have specified message code.
 *
 * @author Isaiah Liu
 *
 * @see IMessage
 */
public abstract class AbstractFreeTextLookup<TType extends ILookupType> extends AbstractFreeTextMessage<TType>
        implements ILookupMessage<TType> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
