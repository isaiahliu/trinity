package org.trinity.common.accessright;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordTokenAuthenticationToken
		extends UsernamePasswordAuthenticationToken implements ITokenAwareAuthentication {

	private static final long serialVersionUID = 1L;

	private final AuthToken token;

	public UsernamePasswordTokenAuthenticationToken(final Object principal,
			final Object credentials, final AuthToken token) {
		super(principal, credentials);
		this.token = token;
	}

	public UsernamePasswordTokenAuthenticationToken(final Object principal,
			final Object credentials, final AuthToken token,
			final Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.token = token;
	}

	@Override
	public AuthToken getToken() {
		return token;
	}
}
