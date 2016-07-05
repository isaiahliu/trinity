package org.trinity.common.exception.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.IMessageResolverChain;
import org.trinity.message.exception.IExceptionMessage;

public class LocalizedExceptionFactory implements IExceptionFactory, ApplicationContextAware {

    private final IMessageResolverChain messageResolver;

    private ApplicationContext applicationContext;

    public LocalizedExceptionFactory(final IMessageResolverChain messageResolver) {
        super();
        this.messageResolver = messageResolver;
    }

    @Override
    public IException createException(final IExceptionMessage exceptionMessage, final String... params) {
        return applicationContext.getBean(IException.class, messageResolver, exceptionMessage, params);
    }

    @Override
    public IException createException(final String message) {
        return applicationContext.getBean(IException.class, messageResolver, message);
    }

    @Override
    public IException createException(final Throwable e, final IExceptionMessage exceptionMessage,
            final String... params) {
        return applicationContext.getBean(IException.class, messageResolver, e, exceptionMessage, params);
    }

    @Override
    public IException createException(final Throwable e, final String message) {
        return applicationContext.getBean(IException.class, messageResolver, e, message);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
