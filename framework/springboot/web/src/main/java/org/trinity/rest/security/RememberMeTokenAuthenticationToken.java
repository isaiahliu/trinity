package org.trinity.rest.security;

import java.util.Collection;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RememberMeTokenAuthenticationToken extends RememberMeAuthenticationToken
		implements ITokenAwareAuthentication {

	private static final long serialVersionUID = 1L;

	private final Token token;

	public RememberMeTokenAuthenticationToken(final String key, final Object principal,
			final Collection<? extends GrantedAuthority> authorities, final Token token) {
		super(key, principal, authorities);
		this.token = token;
	}

	@Override
	public Token getToken() {
		return token;
	}
}
