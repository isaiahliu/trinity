package org.trinity.yqyl.rest.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.trinity.rest.security.AbstractPreAuthenticationFilter;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Accessright;

@Component
public class UserService implements UserDetailsService {
	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			return null;
		}

		final org.trinity.yqyl.repository.business.entity.User userEntity = userRepository.findOneByUsername(username);

		if (userEntity == null) {
			return null;
		}

		final List<Accessright> accessRights = new ArrayList<>();
		userEntity.getRoles().stream().map(item -> item.getAccessrights()).reduce(accessRights, (a, b) -> {
			a.addAll(b);
			return a;
		});

		final List<GrantedAuthority> authorities = accessRights.stream().map(item -> (GrantedAuthority) (item.getName()))
				.collect(Collectors.toList());

		authorities.add(new SimpleGrantedAuthority(AbstractPreAuthenticationFilter.ROLE_ANONYMOUS_WITH_TOKEN));

		return new User(username, userEntity.getPassword(), authorities);
	}
}