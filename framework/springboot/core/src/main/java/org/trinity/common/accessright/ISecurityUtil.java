package org.trinity.common.accessright;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.trinity.common.exception.IException;

public interface ISecurityUtil<T extends IAccessRight<?>> {
    public static enum CheckMode {
        ANY,
        ALL
    }

    void checkAccessRight(final CheckMode checkMode, @SuppressWarnings("unchecked") final T... accessRights) throws IException;

    void checkAuthorizationEnabled(boolean enabled) throws IException;

    void checkSuperUser() throws IException;

    default void deny() throws IException {
        checkAuthorizationEnabled(false);
    }

    Collection<? extends GrantedAuthority> getAuthorities();

    AuthToken getCurrentToken() throws IException;

    boolean hasAccessRight(final CheckMode checkMode, @SuppressWarnings("unchecked") final T... accessRights);

    boolean hasAccessRightByName(String accessRightName);

    boolean hasToken();

    boolean isSuperUser();
}
