package org.trinity.rest.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordTokenAuthenticationToken
		extends UsernamePasswordAuthenticationToken implements ITokenAwareAuthentication {

	private static final long serialVersionUID = 1L;

	private final Token token;

	public UsernamePasswordTokenAuthenticationToken(final Object principal,
			final Object credentials, final Token token) {
		super(principal, credentials);
		this.token = token;
	}

	public UsernamePasswordTokenAuthenticationToken(final Object principal,
			final Object credentials, final Token token,
			final Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.token = token;
	}

	@Override
	public Token getToken() {
		return token;
	}
}
