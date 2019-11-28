package com.alco.pubslist.repository;

import com.alco.pubslist.entities.Place;
import com.alco.pubslist.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
public interface PlaceRepository extends CrudRepository<Place, UUID> {

}
