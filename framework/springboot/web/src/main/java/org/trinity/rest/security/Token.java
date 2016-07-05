package org.trinity.rest.security;

import java.util.Date;

public class Token {
    public static Token createEmptyToken(final String token) {
        return new Token(token, TokenStatus.NOT_SUPPORTED, null, null, null, null);
    }

    private final String token;

    private TokenStatus status;

    private Date firstActiveTimestamp;

    private Date lastActiveTimestamp;

    private Date expireTimestamp;

    private String username;

    public Token(final String token) {
        this.token = token;
    }

    public Token(final String token, final TokenStatus status, final Date firstActiveTimestamp,
            final Date lastActiveTimestamp, final Date expireTimestamp, final String username) {
        this(token);
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

    public void setExpireTimestamp(final Date expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }

    public void setFirstActiveTimestamp(final Date firstActiveTimestamp) {
        this.firstActiveTimestamp = firstActiveTimestamp;
    }

    public void setLastActiveTimestamp(final Date lastActiveTimestamp) {
        this.lastActiveTimestamp = lastActiveTimestamp;
    }

    public void setStatus(final TokenStatus status) {
        this.status = status;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
