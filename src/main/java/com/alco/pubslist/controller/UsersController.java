package com.alco.pubslist.controller;

import com.alco.pubslist.entities.User;
import com.alco.pubslist.repository.UserRepository;
import com.alco.pubslist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UsersController {


    @Autowired
    UserService userService;

    @Secured(value = "ROLE_ADMIN")
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
