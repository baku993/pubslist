package com.alco.pubslist.controller;

import com.alco.pubslist.entities.User;
import com.alco.pubslist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.alco.pubslist.security.SecurityConstants.SIGN_UP_URL;
import static com.alco.pubslist.security.SecurityConstants.USERS_URL;

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

	@PostMapping(SIGN_UP_URL)
	User save(@RequestBody User newUser) {

		return userService.save(newUser);
	}
}
