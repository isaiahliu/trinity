package org.trinity.yqyl.rest.util;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.trinity.rest.security.AbstractTokenFilter;
import org.trinity.rest.security.Token;
import org.trinity.rest.security.TokenStatus;
import org.trinity.yqyl.common.message.lookup.SystemAttributeKey;
import org.trinity.yqyl.repository.business.dataaccess.ISystemAttributeRepository;
import org.trinity.yqyl.repository.business.dataaccess.ITokenRepository;
import org.trinity.yqyl.repository.business.entity.SystemAttribute;
import org.trinity.yqyl.repository.business.entity.User;

public class TokenFilter extends AbstractTokenFilter {
    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private ISystemAttributeRepository systemAttributeRepository;

    public TokenFilter(final UserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    @Transactional
    protected Token getToken(final String token) {
        final org.trinity.yqyl.repository.business.entity.Token tokenEntity = tokenRepository.findOneByToken(token);

        final Token result = new Token(token);

        try {
            if (tokenEntity == null) {
                result.setStatus(TokenStatus.NOT_EXISTS);
            }

            result.setFirstActiveTimestamp(tokenEntity.getActiveTimestamp());
            result.setLastActiveTimestamp(tokenEntity.getLastActiveTimestamp());

            final User user = tokenEntity.getUser();
            if (user != null) {
                result.setUsername(user.getUsername());
            }

            switch (tokenEntity.getStatus()) {
            case AUTHENTICATED:
                result.setStatus(TokenStatus.AUTHENTICATED);
                final SystemAttribute attribute = systemAttributeRepository
                        .findOneByKey(SystemAttributeKey.TOKEN_EXPIRE_DAYS);

                if (attribute != null) {
                    final int expireDays = Integer.parseInt(attribute.getValue());

                    final Calendar calendar = Calendar.getInstance();
                    calendar.setTime(tokenEntity.getLastActiveTimestamp());

                    calendar.add(Calendar.DATE, expireDays);

                    final Date expireDate = calendar.getTime();

                    if (new Date().after(expireDate)) {
                        result.setStatus(TokenStatus.EXPIRED);
                    }
                }

                if (result.getStatus() == TokenStatus.AUTHENTICATED) {
                    tokenEntity.setLastActiveTimestamp(new Date());
                    tokenRepository.save(tokenEntity);
                }

                break;
            case EXPIRED:
                result.setStatus(TokenStatus.EXPIRED);
                break;
            case LOGGED_BY_OTHERS:
                result.setStatus(TokenStatus.LOGGED_BY_OTHERS);
                break;
            case PASSWORD_CHANGED:
                result.setStatus(TokenStatus.PASSWORD_CHANGED);
                break;
            case UNAUTHENTICATED:
                result.setStatus(TokenStatus.UNAUTHENTICATED);
                break;
            }
        } catch (final Throwable e) {
            result.setStatus(TokenStatus.NOT_EXISTS);
            result.setUsername(null);
        }
        return result;
    }

}
