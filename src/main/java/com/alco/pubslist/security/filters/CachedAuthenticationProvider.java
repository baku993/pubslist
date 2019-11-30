package com.alco.pubslist.security.filters;

import com.alco.pubslist.Helper;
import com.alco.pubslist.entities.User;
import com.alco.pubslist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CachedAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Value("pubslist.salt")
	private String salt;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = userRepository.findDistinctFirstByUsername(name);

		if (user == null) {
			throw new UsernameNotFoundException("Authentication failed for " + name);
		}

		if (isAuthenticated(password, user)) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

			return new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
		}
		else {
			throw new BadCredentialsException("Username/Password combination is incorect");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private boolean isAuthenticated(String password, User user) {

		return user.getPassword().equals(Helper.cacheData(password, salt));
	}

}
