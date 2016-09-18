package org.trinity.common.accessright;

public enum TokenAuthenticationStatus {
	AUTHENTICATED,
	TOKEN_IS_EXPIRED,
	UNAUTHENTICATED,
	LOGGED_BY_OTHERS,
	USER_IS_DISABLED,
	PASSWORD_CHANGED,
	NOT_EXISTS,
	NOT_SUPPORTED
}