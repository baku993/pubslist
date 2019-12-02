package com.alco.pubslist.services;

import com.alco.pubslist.Helper;
import com.alco.pubslist.entities.User;
import com.alco.pubslist.exceptions.BaseException;
import com.alco.pubslist.repository.UserRepository;
import com.alco.pubslist.security.RestResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserService {

	@Autowired
	private UserRepository repository;

	@Value("pubslist.salt")
	private String salt;

	public User save(User user) {

		if (user.getUsername() == null || user.getPassword() == null) {
			throw new BaseException(RestResponses.MISSING_USERNAME_OR_PASSWORD);
		}
		else if (repository.countUsersByUsername(user.getUsername()) > 0) {
			throw new BaseException(RestResponses.USERNAME_IS_ALREADY_USED);
		}

		user.setPassword(Helper.cacheData(user.getPassword(), salt));

		User newUser = repository.save(user);
		newUser.setPassword(null);
		return newUser;
	}

	public Iterable<User> findAll() {

		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.peek(user -> user.setPassword(null)).collect(Collectors.toList());
	}
}
