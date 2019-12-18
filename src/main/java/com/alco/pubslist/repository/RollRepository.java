package com.alco.pubslist.repository;

import com.alco.pubslist.entities.Roll;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RollRepository extends CrudRepository<Roll, Integer> {

	Optional<Roll> findFirstByRolledByOrderByRolledAtDesc( String username);
}
