package org.trinity.yqyl.rest.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.IMessageResolverChain;
import org.trinity.rest.security.ITokenAwareAuthentication;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.rest.accessright.AccessRight;

public class SecurityUtil {
    public static enum CheckMode {
        ANY,
        ALL
    }

    private final IMessageResolverChain messageResolver;
    private final IExceptionFactory exceptionFactory;

    private final AccessRight[] superUsers;

    public SecurityUtil(final IMessageResolverChain messageResolver, final IExceptionFactory exceptionFactory,
            final AccessRight... superUsers) {
        super();
        this.messageResolver = messageResolver;
        this.exceptionFactory = exceptionFactory;
        this.superUsers = superUsers;
    }

    public void checkAccessRight(final CheckMode checkMode, final AccessRight... accessRights) throws IException {
        final Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();

        for (final AccessRight superUser : superUsers) {
            if (authorities.contains(superUser)) {
                return;
            }
        }

        int hitCount = 0;
        for (final AccessRight accessRight : accessRights) {
            if (authorities.contains(accessRight)) {
                hitCount++;
            }
        }

        final String accessrightNames = String.join(",",
                Arrays.stream(accessRights).map(item -> messageResolver.getMessage(item)).collect(Collectors.toList()));

        switch (checkMode) {
        case ALL:
            if (hitCount != accessRights.length) {
                throw exceptionFactory.createException(ErrorMessage.NOT_ALL_ACCESS_IS_GRANTED, accessrightNames);
            }
            break;
        case ANY:
            if (hitCount == 0) {
                throw exceptionFactory.createException(ErrorMessage.NONE_ACCESS_IS_GRANTED, accessrightNames);
            }
            break;
        default:
            break;
        }
    }

    public String getCurrentUser() {
        return ((ITokenAwareAuthentication) (SecurityContextHolder.getContext().getAuthentication())).getToken()
                .getUsername();
    }
}
