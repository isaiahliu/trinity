package org.trinity.yqyl.rest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.trinity.common.accessright.AuthToken;
import org.trinity.rest.security.AbstractPreAuthenticationFilter;
import org.trinity.yqyl.process.controller.base.ITokenProcessController;

public class TokenFilter extends AbstractPreAuthenticationFilter {

    @Autowired
    private ITokenProcessController tokenProcessController;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected AuthToken getToken(final String token) {
        return tokenProcessController.preAuth(token);

    }

    @Override
    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

}
