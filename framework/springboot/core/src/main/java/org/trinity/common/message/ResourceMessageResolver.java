package org.trinity.common.message;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.trinity.message.IMessageResolver;

public class ResourceMessageResolver implements IMessageResolver {
    private final MessageSource resourceMessageSource;

    public ResourceMessageResolver(final MessageSource resourceMessageSource) {
        super();
        this.resourceMessageSource = resourceMessageSource;
    }

    @Override
    public String getMessage(final String message, final Locale locale, final String... args) {
        try {
            return resourceMessageSource.getMessage(message, args, locale);
        } catch (final NoSuchMessageException e) {
            return null;
        }
    }

    @Override
    public void refresh() {
        if (resourceMessageSource instanceof ReloadableResourceBundleMessageSource) {
            ((ReloadableResourceBundleMessageSource) resourceMessageSource).clearCache();
        }
    }
}
