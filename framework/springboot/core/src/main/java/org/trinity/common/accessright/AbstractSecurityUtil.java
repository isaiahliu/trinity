package org.trinity.common.accessright;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.IMessageResolverChain;
import org.trinity.message.exception.GeneralErrorMessage;

public abstract class AbstractSecurityUtil<T extends IAccessRight<?>> implements ISecurityUtil<T> {

    private final IMessageResolverChain messageResolver;

    private final IExceptionFactory exceptionFactory;
    private final T[] superUsers;

    @SafeVarargs
    public AbstractSecurityUtil(final IMessageResolverChain messageResolver, final IExceptionFactory exceptionFactory,
            final T... superUsers) {
        super();
        this.messageResolver = messageResolver;
        this.exceptionFactory = exceptionFactory;
        this.superUsers = superUsers;
    }

    @Override
    public void checkAccessRight(final CheckMode checkMode, @SuppressWarnings("unchecked") final T... accessRights) throws IException {
        final Collection<? extends GrantedAuthority> authorities = getAuthorities();

        for (final IAccessRight<?> superUser : superUsers) {
            if (contains(authorities, superUser)) {
                return;
            }
        }

        int hitCount = 0;
        for (final IAccessRight<?> accessRight : accessRights) {
            if (contains(authorities, accessRight)) {
                hitCount++;
            }
        }

        final String accessrightNames = String.join(",",
                Arrays.stream(accessRights).map(item -> messageResolver.getMessage(item)).collect(Collectors.toList()));

        switch (checkMode) {
        case ALL:
            if (hitCount != accessRights.length) {
                throw exceptionFactory.createException(GeneralErrorMessage.NOT_ALL_ACCESS_IS_GRANTED, accessrightNames);
            }
            break;
        case ANY:
            if (accessRights.length > 0 && hitCount == 0) {
                throw exceptionFactory.createException(GeneralErrorMessage.NONE_ACCESS_IS_GRANTED, accessrightNames);
            }
            break;
        default:
            break;
        }
    }

    @Override
    public void checkAuthorizationEnabled(final boolean enabled) throws IException {
        if (!enabled) {
            throw exceptionFactory.createException(GeneralErrorMessage.AUTHERIZATION_DISABLED);
        }
    }

    @Override
    public void checkSuperUser() throws IException {
        if (!isSuperUser()) {
            throw exceptionFactory.createException(GeneralErrorMessage.SUPER_USER_IS_REQUIRED);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    @Override
    public AuthToken getCurrentToken() throws IException {
        if (!hasToken()) {
            throw exceptionFactory.createException(GeneralErrorMessage.TOKEN_IS_MISSING);
        }
        return ((ITokenAwareAuthentication) (SecurityContextHolder.getContext().getAuthentication())).getToken();
    }

    @Override
    public boolean hasAccessRight(final CheckMode checkMode, @SuppressWarnings("unchecked") final T... accessRights) {
        try {
            checkAccessRight(checkMode, accessRights);
            return true;
        } catch (final IException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean hasAccessRightByName(final String accessRightName) {
        final T accessRight = getAccessRightByName(accessRightName);

        return hasAccessRight(CheckMode.ALL, accessRight);
    }

    @Override
    public boolean hasToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        if (!(authentication instanceof ITokenAwareAuthentication)) {
            return false;
        }

        if (((ITokenAwareAuthentication) authentication).getToken() == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isSuperUser() {
        return hasAccessRight(CheckMode.ANY, superUsers);
    }

    private boolean contains(final Collection<? extends GrantedAuthority> authorities, final IAccessRight<?> accessRight) {
        if (accessRight == null) {
            return false;
        }

        if (authorities.contains(accessRight)) {
            return true;
        }

        return contains(authorities, accessRight.getParentAccessRight());
    }

    protected abstract T getAccessRightByName(String accessRightName);
}
