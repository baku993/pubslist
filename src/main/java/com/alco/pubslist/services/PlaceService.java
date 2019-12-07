package com.alco.pubslist.services;

import com.alco.pubslist.configuration.UserContext;
import com.alco.pubslist.entities.Place;
import com.alco.pubslist.exceptions.BaseException;
import com.alco.pubslist.repository.PlaceRepository;
import com.alco.pubslist.security.RestResponses;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

@Component
public class PlaceService {

	@Autowired
	private PlaceRepository repository;

	public Place suggestPlace(Place place) {

		if (place.getName() == null || place.getAddress() == null) {
			throw new BaseException(RestResponses.MISSING_PLACE_NAME_OR_ADDRESS);
		}
		place.setEnabled(true);
		place.setApproved(false);
		return repository.save(place);
	}

	public Iterable<Place> findAll() {

		return repository.findAll();
	}

	public void update(BufferedReader reader, Integer id) {

		Place place = findPlaceById(id);
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Read and map JSON to entity from DB, merged object as output
			Place updatedPlace = objectMapper.readerForUpdating(place).readValue(reader);

			// Only admin or user who owns this place can update
			// TODO: Add check for user owner
			if (!UserContext.isAdmin()) {
				throw new BaseException(RestResponses.ACCESS_DENIED);
			}

			// Required fields should be filled
			// TODO: Other fields should be checked, everything can can be changed on UI
			if (updatedPlace.getName() == null || updatedPlace.getAddress() == null) {
				throw new BaseException(RestResponses.MISSING_PLACE_NAME_OR_ADDRESS);
			}

			repository.save(updatedPlace);
		}
		catch (IOException e) {
			throw new BaseException(RestResponses.MALFORMED_JSON);
		}
	}

	public void delete(Integer id) {

		Place place = findPlaceById(id);

		// Only admin or user who owns this place can update
		// TODO: Add check for user owner
		if (!UserContext.isAdmin()) {
			throw new BaseException(RestResponses.ACCESS_DENIED);
		}

		repository.deleteById(id);
	}

	private Place findPlaceById(Integer id) {

		Optional<Place> optionalPlace = repository.findById(id);

		if (!optionalPlace.isPresent()) {
			throw new BaseException(RestResponses.NO_PLACE_FOUND);
		}

		return optionalPlace.get();
	}
}
