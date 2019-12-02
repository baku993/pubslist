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

import static com.alco.pubslist.security.SecurityConstants.SING_UP_URL;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;

	@Secured("ROLE_ADMIN")
	@GetMapping("users")
	@ResponseBody
	Iterable<User> all() {

		return userService.findAll();
	}

	@PostMapping(SING_UP_URL)
	User save(@RequestBody User newUser) {

		return userService.save(newUser);
	}
}
