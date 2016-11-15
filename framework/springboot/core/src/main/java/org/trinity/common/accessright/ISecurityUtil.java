package org.trinity.common.accessright;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.trinity.common.exception.IException;

public interface ISecurityUtil<T extends IAccessRight<?>> {

    default void checkAccessRight(final T accessRight) throws IException {
        checkAccessRight(accessRight, true);
    }

    void checkAccessRight(T accessRight, boolean checkAncenstors) throws IException;

    void checkAuthorizationEnabled(boolean enabled) throws IException;

    default void deny() throws IException {
        checkAuthorizationEnabled(false);
    }

    Collection<? extends GrantedAuthority> getAuthorities();

    AuthToken getCurrentToken() throws IException;

    default boolean hasAccessRight(final T accessRight) {
        return hasAccessRight(accessRight, true);
    }

    boolean hasAccessRight(T accessRight, boolean checkAncenstors);

    default boolean hasAccessRightByName(final String accessRightName) {
        return hasAccessRightByName(accessRightName);
    }

    boolean hasAccessRightByName(String accessRightName, boolean checkAncestors);

    boolean hasToken();

}
