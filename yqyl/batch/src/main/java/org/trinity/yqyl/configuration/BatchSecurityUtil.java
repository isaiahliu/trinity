package org.trinity.yqyl.configuration;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.trinity.common.accessright.AuthToken;
import org.trinity.common.accessright.IAccessRight;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;

@Component
public class BatchSecurityUtil implements ISecurityUtil<IAccessRight<?>> {
    @Override
    public void checkAccessRight(final CheckMode checkMode, final IAccessRight<?>... accessRights) throws IException {
    }

    @Override
    public void checkAuthorizationEnabled(final boolean enabled) throws IException {
    }

    @Override
    public void checkSuperUser() throws IException {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public AuthToken getCurrentToken() throws IException {
        return new AuthToken("");
    }

    @Override
    public boolean hasAccessRight(final CheckMode checkMode, final IAccessRight<?>... accessRights) {
        return true;
    }

    @Override
    public boolean hasAccessRightByName(final String accessRightName) {
        return true;
    }

    @Override
    public boolean hasToken() {
        return true;
    }

    @Override
    public boolean isSuperUser() {
        return true;
    }
}
