package org.trinity.yqyl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.trinity.rest.security.AbstractTokenFilter;
import org.trinity.rest.security.ITokenAwareAuthentication;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AbstractTokenFilter tokenFilter;

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AuthenticationProvider() {

            @Override
            public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
                return authentication;
            }

            @Override
            public boolean supports(final Class<?> authentication) {
                return ITokenAwareAuthentication.class.isAssignableFrom(authentication);
            }
        });
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterAfter(tokenFilter, SecurityContextPersistenceFilter.class).authorizeRequests()
                .antMatchers("/security/token").permitAll().antMatchers("/security/authenticate")
                .hasAuthority(AbstractTokenFilter.ROLE_ANONYMOUS_WITH_TOKEN).anyRequest().authenticated();
    }
}