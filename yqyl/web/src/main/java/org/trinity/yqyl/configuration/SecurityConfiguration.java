package org.trinity.yqyl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.trinity.yqyl.web.util.SessionFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private SessionFilter sessionFilter;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().and().addFilterAfter(sessionFilter, SecurityContextPersistenceFilter.class)
				.authorizeRequests()
				.antMatchers("/ajax/user/authenticate", "/user/register", "/ajax/user/register", "/ajax/user/logout", "/static/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/user/login").defaultSuccessUrl("/home", true)
				.permitAll();
	}
}
