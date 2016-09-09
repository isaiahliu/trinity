package org.trinity.common.accessright;

import java.util.Date;

public class AuthToken {
    public static AuthToken createEmptyToken(final String token) {
        return new AuthToken(token, TokenAuthenticationStatus.NOT_SUPPORTED, null, null, null, null, null);
    }

    private final String token;

    private TokenAuthenticationStatus status;

    private Date firstActiveTimestamp;

    private Date lastActiveTimestamp;

    private Date expireTimestamp;

    private String username;

    private String userDetailKey;

    public AuthToken(final String token) {
        this.token = token;
    }

    public AuthToken(final String token, final TokenAuthenticationStatus status, final Date firstActiveTimestamp,
            final Date lastActiveTimestamp, final Date expireTimestamp, final String username, final String userDetailKey) {
        this(token);
        this.status = status;
        this.firstActiveTimestamp = firstActiveTimestamp;
        this.lastActiveTimestamp = lastActiveTimestamp;
        this.expireTimestamp = expireTimestamp;
        this.username = username;
        this.userDetailKey = userDetailKey;
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

    public TokenAuthenticationStatus getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public String getUserDetailKey() {
        return userDetailKey;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return status == TokenAuthenticationStatus.AUTHENTICATED;
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

    public void setStatus(final TokenAuthenticationStatus status) {
        this.status = status;
    }

    public void setUserDetailKey(final String userDetailKey) {
        this.userDetailKey = userDetailKey;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
