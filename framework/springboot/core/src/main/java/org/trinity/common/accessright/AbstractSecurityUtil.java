package org.trinity.common.accessright;

import java.util.Collection;

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

    public AbstractSecurityUtil(final IMessageResolverChain messageResolver, final IExceptionFactory exceptionFactory) {
        super();
        this.messageResolver = messageResolver;
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    public void checkAccessRight(final T accessRight, final boolean checkAncenstors) throws IException {
        if (!hasAccessRight(accessRight, checkAncenstors)) {
            throw exceptionFactory.createException(GeneralErrorMessage.NONE_ACCESS_IS_GRANTED, messageResolver.getMessage(accessRight));
        }
    }

    @Override
    public void checkAuthorizationEnabled(final boolean enabled) throws IException {
        if (!enabled) {
            throw exceptionFactory.createException(GeneralErrorMessage.AUTHERIZATION_DISABLED);
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
    public boolean hasAccessRight(final T accessRight, final boolean checkAncenstors) {
        return contains(getAuthorities(), accessRight, checkAncenstors);
    }

    @Override
    public boolean hasAccessRightByName(final String accessRightName, final boolean checkAncestors) {
        final T accessRight = getAccessRightByName(accessRightName);

        return hasAccessRight(accessRight, checkAncestors);
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

    private boolean contains(final Collection<? extends GrantedAuthority> authorities, final IAccessRight<?> accessRight,
            final boolean checkAncenstors) {
        if (accessRight == null) {
            return false;
        }

        if (authorities.contains(accessRight)) {
            return true;
        }
        if (checkAncenstors) {
            return contains(authorities, accessRight.getParentAccessRight(), checkAncenstors);
        } else {
            return false;
        }
    }

    protected abstract T getAccessRightByName(String accessRightName);
}
