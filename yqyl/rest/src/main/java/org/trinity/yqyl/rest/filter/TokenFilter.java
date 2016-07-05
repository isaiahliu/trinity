package org.trinity.yqyl.rest.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.trinity.rest.security.AbstractTokenFilter;
import org.trinity.rest.security.Token;
import org.trinity.rest.security.TokenStatus;
import org.trinity.yqyl.repository.business.dataaccess.ITokenRepository;

public class TokenFilter extends AbstractTokenFilter {
    @Autowired
    private ITokenRepository tokenRepository;

    public TokenFilter(final UserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    protected Token getToken(final String token) {
        final org.trinity.yqyl.repository.business.entity.Token tokenEntity = tokenRepository.findOneByToken(token);

        if (tokenEntity == null) {
            return new Token(token, TokenStatus.NOT_EXISTS, null, null, null, null);
        }

        switch (tokenEntity.getStatus()) {
        case AUTHENTICATED:
            return new Token(token, TokenStatus.NOT_EXISTS, null, null, null, null);
        case EXPIRED:
            return new Token(token, TokenStatus.NOT_EXISTS, null, null, null, null);
        case LOGGED_BY_OTHERS:
            return new Token(token, TokenStatus.NOT_EXISTS, null, null, null, null);
        case PASSWORD_CHANGED:
            return new Token(token, TokenStatus.NOT_EXISTS, null, null, null, null);
        case UNAUTHENTICATED:
            return new Token(token, TokenStatus.NOT_EXISTS, null, null, null, null);
        }
        return Token.getEmptyToken();
    }

}
