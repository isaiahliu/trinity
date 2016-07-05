package org.trinity.yqyl.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.LocalizedException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.common.exception.factory.LocalizedExceptionFactory;
import org.trinity.common.message.MessageResolverChain;
import org.trinity.common.message.ResourceMessageResolver;
import org.trinity.message.IMessageResolver;
import org.trinity.message.IMessageResolverChain;
import org.trinity.message.exception.IExceptionMessage;

@Configuration
public class CommonConfiguration {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IException getException(final IMessageResolverChain messageResolver) {
        return new LocalizedException(messageResolver);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IException getException(final IMessageResolverChain messageResolver,
            final IExceptionMessage exceptionMessage, final String... params) {
        return new LocalizedException(messageResolver, exceptionMessage, params);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IException getException(final IMessageResolverChain messageResolver, final String message) {
        return new LocalizedException(messageResolver, message);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IException getException(final IMessageResolverChain messageResolver, final Throwable e,
            final IExceptionMessage exceptionMessage, final String... params) {
        return new LocalizedException(messageResolver, e, exceptionMessage, params);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IException getException(final IMessageResolverChain messageResolver, final Throwable e,
            final String message) {
        return new LocalizedException(messageResolver, e, message);
    }

    @Bean
    public IExceptionFactory getExceptionFactory(final IMessageResolverChain messageResolver) {
        return new LocalizedExceptionFactory(messageResolver);
    }

    @Bean
    public IMessageResolver getMessageResolver(final MessageSource messageResolver) {
        return new ResourceMessageResolver(messageResolver);
    }

    @Bean
    public IMessageResolverChain getMessageResolverChain(final IMessageResolver... messageResolvers) {
        return new MessageResolverChain(messageResolvers);
    }
}
