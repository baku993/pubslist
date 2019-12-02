package com.alco.pubslist.repository;

import com.alco.pubslist.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

	User findDistinctFirstByUsername(String username);

	Integer countUsersByUsername(String username);
}
