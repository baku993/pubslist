package com.alco.pubslist.repository;

import com.alco.pubslist.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findDistinctFirstByUsername(String username);

	boolean existsByUsername(String username);
}
