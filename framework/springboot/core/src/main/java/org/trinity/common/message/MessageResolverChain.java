package org.trinity.common.message;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.trinity.message.IMessage;
import org.trinity.message.IMessageResolver;
import org.trinity.message.IMessageResolverChain;

public class MessageResolverChain implements IMessageResolverChain {
    private final IMessageResolver[] messageResolvers;

    public MessageResolverChain(final IMessageResolver[] messageResolvers) {
        this.messageResolvers = messageResolvers;
    }

    @Override
    public String getMessage(final IMessage message) {
        if (message == null) {
            return "";
        }

        return getMessage(message.getMessageCodeWithPrefix());
    }

    @Override
    public String getMessage(final IMessage message, final Locale locale) {
        if (message == null) {
            return "";
        }

        return getMessage(message.getMessageCodeWithPrefix(), locale);
    }

    @Override
    public String getMessage(final IMessage message, final Locale locale, final String... args) {
        if (message == null) {
            return "";
        }

        return getMessage(message.getMessageCodeWithPrefix(), locale, args);
    }

    @Override
    public String getMessage(final IMessage message, final String... args) {
        if (message == null) {
            return "";
        }

        return getMessage(message.getMessageCodeWithPrefix(), args);
    }

    @Override
    public String getMessage(final String message) {
        return getMessage(message, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessage(final String message, final Locale locale) {
        return getMessage(message, locale, new String[] {});
    }

    @Override
    public String getMessage(final String message, final Locale locale, final String... args) {
        for (final IMessageResolver messageResolver : getMessageResolvers()) {
            final String desc = messageResolver.getMessage(message, locale, args);
            if (desc != null) {
                return desc;
            }
        }

        return message;
    }

    @Override
    public String getMessage(final String message, final String... args) {
        return getMessage(message, LocaleContextHolder.getLocale(), args);
    }

    @Override
    public IMessageResolver[] getMessageResolvers() {
        return messageResolvers;
    }
}
