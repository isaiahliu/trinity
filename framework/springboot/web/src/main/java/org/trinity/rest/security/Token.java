package org.trinity.rest.security;

import java.util.Date;

public class Token {
	private static Token emptyToken;

	public static Token getEmptyToken() {
		if (emptyToken == null) {
			emptyToken = new Token(null, TokenStatus.NOT_SUPPORTED, null, null, null, null);
		}
		return emptyToken;
	}

	private final String token;

	private final TokenStatus status;

	private final Date firstActiveTimestamp;

	private final Date lastActiveTimestamp;

	private final Date expireTimestamp;

	private final String username;

	public Token(final String token, final TokenStatus status, final Date firstActiveTimestamp,
			final Date lastActiveTimestamp, final Date expireTimestamp,
			final String username) {
		super();
		this.token = token;
		this.status = status;
		this.firstActiveTimestamp = firstActiveTimestamp;
		this.lastActiveTimestamp = lastActiveTimestamp;
		this.expireTimestamp = expireTimestamp;
		this.username = username;
	}

	public Date getExpireTimestamp() {
		return expireTimestamp;
	}

	public Date getFirstActiveTimestamp() {
		return firstActiveTimestamp;
	}

	public Date getLastActiveTimestamp() {
		return lastActiveTimestamp;
	}

	public TokenStatus getStatus() {
		return status;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}
}
