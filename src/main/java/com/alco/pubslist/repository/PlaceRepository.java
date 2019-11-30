package com.alco.pubslist.repository;

import com.alco.pubslist.entities.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlaceRepository extends CrudRepository<Place, UUID> {

}
