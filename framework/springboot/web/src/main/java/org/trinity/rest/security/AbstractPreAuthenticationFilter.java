package org.trinity.rest.security;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.trinity.common.accessright.AnonymousTokenAuthenticationToken;
import org.trinity.common.accessright.AuthToken;
import org.trinity.common.accessright.UsernamePasswordTokenAuthenticationToken;

public abstract class AbstractPreAuthenticationFilter extends OncePerRequestFilter {
    private final static String TOKEN_TAG = "Token";
    public final static String ROLE_ANONYMOUS_WITH_TOKEN = "ROLE_ANONYMOUS_WITH_TOKEN";
    public final static String ROLE_ANONYMOUS_WITHOUT_TOKEN = "ROLE_ANONYMOUS_WITHOUT_TOKEN";
    private final String key;
    private final String priciple;
    private final List<GrantedAuthority> noTokenAuthorities;
    private final List<GrantedAuthority> tokenAuthorities;

    public AbstractPreAuthenticationFilter() {
        key = UUID.randomUUID().toString();
        priciple = "anonymousUser";
        noTokenAuthorities = AuthorityUtils.createAuthorityList(ROLE_ANONYMOUS_WITHOUT_TOKEN);
        tokenAuthorities = AuthorityUtils.createAuthorityList(ROLE_ANONYMOUS_WITH_TOKEN);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {
        if (isResourcePath(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        AuthToken token = null;
        UserDetails userDetail = null;

        final String tokenName = parseTokenName(request, response);

        token = getToken(tokenName);

        if (token == null) {
            SecurityContextHolder.getContext()
                    .setAuthentication(new AnonymousTokenAuthenticationToken(key, priciple, noTokenAuthorities, null));
        } else {
            if (!StringUtils.isEmpty(token.getUserDetailKey())) {
                userDetail = getUserDetailsService().loadUserByUsername(token.getUserDetailKey());
            }

            if (userDetail != null) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordTokenAuthenticationToken(token.getUsername(), "", token, userDetail.getAuthorities()));
            } else {
                token = AuthToken.createEmptyToken(tokenName);
                SecurityContextHolder.getContext()
                        .setAuthentication(new AnonymousTokenAuthenticationToken(key, priciple, tokenAuthorities, token));
            }
        }

        filterChain.doFilter(request, response);
    }

    protected abstract AuthToken getToken(String token);

    protected abstract UserDetailsService getUserDetailsService();

    protected boolean isResourcePath(final String path) {
        return false;
    }

    protected String parseTokenName(final HttpServletRequest request, final HttpServletResponse response) {
        return request.getHeader(TOKEN_TAG);
    }
}
