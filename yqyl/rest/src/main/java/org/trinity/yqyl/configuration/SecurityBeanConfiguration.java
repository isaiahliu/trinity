package org.trinity.yqyl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.trinity.rest.security.AbstractTokenFilter;
import org.trinity.yqyl.rest.filter.TokenFilter;

@Configuration
public class SecurityBeanConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AbstractTokenFilter getTokenFilter() {
		return new TokenFilter(userDetailsService);
	}
}
