package org.trinity.common.accessright;

import org.springframework.security.core.Authentication;

public interface ITokenAwareAuthentication extends Authentication {
    AuthToken getToken();
}
