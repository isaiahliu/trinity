package org.trinity.common.exception;

import org.trinity.message.IMessageResolverChain;
import org.trinity.message.exception.IExceptionMessage;

/**
 * @author Isaiah Liu
 *
 *         A message class which supports multi locale.
 *
 * @see IException
 */
public class LocalizedException extends IException {
    private static final long serialVersionUID = 1L;

    private final IMessageResolverChain messageResolver;

    public LocalizedException(final IMessageResolverChain messageResolver) {
        super();

        this.messageResolver = messageResolver;
    }

    public LocalizedException(final IMessageResolverChain messageResolver, final IExceptionMessage exceptionMessage,
            final String... params) {
        super(exceptionMessage, params);

        this.messageResolver = messageResolver;
    }

    public LocalizedException(final IMessageResolverChain messageResolver, final String message) {
        super(message);

        this.messageResolver = messageResolver;
    }

    public LocalizedException(final IMessageResolverChain messageResolver, final Throwable e,
            final IExceptionMessage exceptionMessage, final String... params) {
        super(e, exceptionMessage, params);

        this.messageResolver = messageResolver;
    }

    public LocalizedException(final IMessageResolverChain messageResolver, final Throwable e, final String message) {
        super(e, message);

        this.messageResolver = messageResolver;
    }

    @Override
    public String getMessage() {
        return String.join("\r\n", getErrorMessages().stream().map(item -> {
            return messageResolver.getMessage(item.getItem1(), item.getItem2());
        }).toArray(String[]::new));
    }
}
