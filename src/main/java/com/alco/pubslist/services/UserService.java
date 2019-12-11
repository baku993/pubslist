package com.alco.pubslist.services;

import com.alco.pubslist.Helper;
import com.alco.pubslist.entities.User;
import com.alco.pubslist.exceptions.BaseException;
import com.alco.pubslist.repository.UserRepository;
import com.alco.pubslist.security.RestResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
		else if (repository.existsByUsername(user.getUsername())) {
			throw new BaseException(RestResponses.USERNAME_IS_ALREADY_USED);
		}

		user.setPassword(Helper.cacheData(user.getPassword(), salt));

		return repository.save(user);
	}

	public Iterable<User> findAll() {

		return repository.findAll();
	}

	public User findById(Integer userId) {

		return repository.findById(userId).orElseThrow(() -> new BaseException(RestResponses.NO_USER_FOUND));
	}

	public User findByUsername(String username) {return repository.findDistinctFirstByUsername(username);}
}
