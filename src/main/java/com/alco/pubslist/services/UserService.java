package com.alco.pubslist.services;

import com.alco.pubslist.Helper;
import com.alco.pubslist.configuration.UserContext;
import com.alco.pubslist.entities.User;
import com.alco.pubslist.exceptions.BaseException;
import com.alco.pubslist.repository.UserRepository;
import com.alco.pubslist.security.RestResponses;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@Component
public class UserService {

	@Autowired
	private UserRepository repository;

	@Value("pubslist.salt")
	private String salt;
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public User save(User user) {

		if (user.getUsername() == null || user.getPassword() == null) {
			throw new BaseException(RestResponses.MISSING_USERNAME_OR_PASSWORD);
		}
		else if (repository.existsByUsername(user.getUsername())) {
			throw new BaseException(RestResponses.USERNAME_IS_ALREADY_USED);
		}

		user.setUsername(user.getUsername().toLowerCase());
		user.setPassword(Helper.cacheData(user.getPassword(), salt));

		return repository.save(user);
	}

	public Iterable<User> findAll() {

		return repository.findAll();
	}

	public User findById(Integer userId) {

		return repository.findById(userId).orElseThrow(() -> new BaseException(RestResponses.NO_USER_FOUND));
	}

	public void update(BufferedReader reader, Integer id) {

		User user = findById(id);

		try {
			// Read and map JSON to entity from DB, merge in object as output
			User updatedUser = OBJECT_MAPPER.readerForUpdating(user).readValue(reader);

			// Only admin or user who owns this place can update in case
			// if the place has not approved yet
			if (!UserContext.isAdmin()
					&& !Objects.equals(user.getId(), UserContext.getUserId())) {
				throw new BaseException(RestResponses.ACCESS_DENIED);
			}

			// Required fields should be filled
			if (updatedUser.getName() == null || updatedUser.getSurname() == null) {
				throw new BaseException(RestResponses.MISSING_REQUIRED_FIELD);
			}

			repository.save(updatedUser);
		}
		catch (IOException e) {
			throw new BaseException(RestResponses.MALFORMED_JSON);
		}
	}

}
