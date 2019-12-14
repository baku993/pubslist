package com.alco.pubslist.controller;

import com.alco.pubslist.configuration.UserContext;
import com.alco.pubslist.entities.User;
import com.alco.pubslist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.alco.pubslist.security.SecurityConstants.SIGN_UP_URL;
import static com.alco.pubslist.security.SecurityConstants.USERS_URL;
import static com.alco.pubslist.security.SecurityConstants.USER_URL;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;

	@Secured("ROLE_ADMIN")
	@GetMapping(USERS_URL)
	@ResponseBody
	Iterable<User> all() {

		return userService.findAll();
	}

	@GetMapping(USER_URL)
	@ResponseBody
	User getCurrentUser() {

		return userService.findById(UserContext.getUserId());
	}

	@PatchMapping(USERS_URL + "/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable("id") Integer id, HttpServletRequest request)
			throws IOException {

		userService.update(request.getReader(), id);
		return ResponseEntity.ok("");
	}

	@PostMapping(SIGN_UP_URL)
	ResponseEntity save(@RequestBody User newUser) {

		userService.save(newUser);

		return ResponseEntity.ok("");
	}
}
