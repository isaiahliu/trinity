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
import org.springframework.web.filter.OncePerRequestFilter;

public abstract class AbstractTokenFilter extends OncePerRequestFilter {
    private final static String TOKEN_TAG = "Token";
    public final static String ROLE_ANONYMOUS_WITH_TOKEN = "ROLE_ANONYMOUS_WITH_TOKEN";
    public final static String ROLE_ANONYMOUS_WITHOUT_TOKEN = "ROLE_ANONYMOUS_WITHOUT_TOKEN";
    private final String key;
    private final String priciple;
    private final List<GrantedAuthority> noTokenAuthorities;
    private final List<GrantedAuthority> tokenAuthorities;
    private final UserDetailsService userDetailsService;

    public AbstractTokenFilter(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        key = UUID.randomUUID().toString();
        priciple = "anonymousUser";
        noTokenAuthorities = AuthorityUtils.createAuthorityList(ROLE_ANONYMOUS_WITHOUT_TOKEN);
        tokenAuthorities = AuthorityUtils.createAuthorityList(ROLE_ANONYMOUS_WITH_TOKEN);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        final String tokenName = parseTokenName(request);

        Token token = null;
        UserDetails userDetail = null;

        if (tokenName != null && tokenName.length() > 0) {
            token = getToken(tokenName);
        }

        if (token == null) {
            SecurityContextHolder.getContext()
                    .setAuthentication(new AnonymousTokenAuthenticationToken(key, priciple, noTokenAuthorities, token));
        } else {
            userDetail = userDetailsService.loadUserByUsername(token.getUsername());
            if (userDetail != null) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordTokenAuthenticationToken(
                        userDetail.getUsername(), "", token, userDetail.getAuthorities()));
            } else {
                token = Token.createEmptyToken(tokenName);
                SecurityContextHolder.getContext().setAuthentication(
                        new AnonymousTokenAuthenticationToken(key, priciple, tokenAuthorities, token));
            }
        }

        filterChain.doFilter(request, response);
    }

    protected abstract Token getToken(String token);

    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    protected String parseTokenName(final HttpServletRequest request) {
        return request.getHeader(TOKEN_TAG);
    }
}
