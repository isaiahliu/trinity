package org.trinity.yqyl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.IMessageResolverChain;
import org.trinity.rest.security.AbstractTokenFilter;
import org.trinity.yqyl.rest.accessright.AccessRight;
import org.trinity.yqyl.rest.util.SecurityUtil;
import org.trinity.yqyl.rest.util.TokenFilter;

@Configuration
public class SecurityBeanConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityUtil getSecurityUtil(final IMessageResolverChain messageResolver,
            final IExceptionFactory exceptionFactory) {
        return new SecurityUtil(messageResolver, exceptionFactory, AccessRight.SUPER_USER);
    }

    @Bean
    public AbstractTokenFilter getTokenFilter() {
        return new TokenFilter(userDetailsService);
    }
}
