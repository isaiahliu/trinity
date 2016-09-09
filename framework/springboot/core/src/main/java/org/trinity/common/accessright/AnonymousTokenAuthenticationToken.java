package org.trinity.common.accessright;

import java.util.Collection;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AnonymousTokenAuthenticationToken extends AnonymousAuthenticationToken
		implements ITokenAwareAuthentication {

	private static final long serialVersionUID = 1L;

	private final AuthToken token;

	public AnonymousTokenAuthenticationToken(final String key, final Object principal,
			final Collection<? extends GrantedAuthority> authorities, final AuthToken token) {
		super(key, principal, authorities);
		this.token = token;
	}

	@Override
	public AuthToken getToken() {
		return token;
	}
}
