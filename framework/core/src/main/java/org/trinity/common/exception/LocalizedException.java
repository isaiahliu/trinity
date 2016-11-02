package org.trinity.common.exception;

import org.trinity.message.IMessageResolverChain;
import org.trinity.message.exception.IErrorMessage;

/**
 * A message class which supports multi locale.
 *
 * @author Isaiah Liu
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

    public LocalizedException(final IMessageResolverChain messageResolver, final IErrorMessage exceptionMessage, final String... params) {
        super(exceptionMessage, params);

        this.messageResolver = messageResolver;
    }

    public LocalizedException(final IMessageResolverChain messageResolver, final String message) {
        super(message);

        this.messageResolver = messageResolver;
    }

    public LocalizedException(final IMessageResolverChain messageResolver, final Throwable e, final IErrorMessage exceptionMessage,
            final String... params) {
        super(e, exceptionMessage, params);

        this.messageResolver = messageResolver;
    }

    public LocalizedException(final IMessageResolverChain messageResolver, final Throwable e, final String message) {
        super(e, message);

        this.messageResolver = messageResolver;
    }

    @Override
    public String getMessage() {
        if (getErrorMessages().isEmpty()) {
            return super.getMessage();
        }
        return String.join("\r\n", getErrorMessages().stream().map(item -> messageResolver.getMessage(item.getItem1(), item.getItem2()))
                .toArray(String[]::new));
    }
}
