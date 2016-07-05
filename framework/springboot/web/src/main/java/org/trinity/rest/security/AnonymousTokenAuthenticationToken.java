package org.trinity.rest.security;

import java.util.Collection;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AnonymousTokenAuthenticationToken extends AnonymousAuthenticationToken
		implements ITokenAwareAuthentication {

	private static final long serialVersionUID = 1L;

	private final Token token;

	public AnonymousTokenAuthenticationToken(final String key, final Object principal,
			final Collection<? extends GrantedAuthority> authorities, final Token token) {
		super(key, principal, authorities);
		this.token = token;
	}

	@Override
	public Token getToken() {
		return token;
	}
}
