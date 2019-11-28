package com.alco.pubslist.services;

import com.alco.pubslist.Helper;
import com.alco.pubslist.entities.User;
import com.alco.pubslist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserRepository repository;

    public User save(User user) {
        user.setPassword(Helper.cacheData(user.getPassword()));
        return repository.save(user);
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }
}
