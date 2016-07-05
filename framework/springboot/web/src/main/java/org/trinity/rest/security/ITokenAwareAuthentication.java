package org.trinity.rest.security;

import org.springframework.security.core.Authentication;

public interface ITokenAwareAuthentication extends Authentication {
    Token getToken();
}
