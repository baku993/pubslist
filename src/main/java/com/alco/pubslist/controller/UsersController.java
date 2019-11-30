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

@RestController
public class UsersController {

	@Autowired
	private UserService userService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/users")
	@ResponseBody
	Iterable<User> all() {

		return userService.findAll();
	}

	@PostMapping("/users")
	User save(@RequestBody User newUser) {

		return userService.save(newUser);
	}
}
