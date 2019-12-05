package com.alco.pubslist.services;

import com.alco.pubslist.entities.Place;
import com.alco.pubslist.exceptions.BaseException;
import com.alco.pubslist.repository.PlaceRepository;
import com.alco.pubslist.security.RestResponses;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
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

	public void update(Map<String, Object> partialUpdate, Integer id) {

		Place place = findPlaceById(id);

		if ((place.isApproved()
				|| partialUpdate.containsKey("enabled")
				|| partialUpdate.containsKey("id")
				|| partialUpdate.containsKey("approved"))
				&& !getRole().equals("ROLE_ADMIN")) {
			throw new BaseException(RestResponses.ACCESS_DENIED);
		}

		if (place.getName() == null || place.getAddress() == null) {
			throw new BaseException(RestResponses.MISSING_PLACE_NAME_OR_ADDRESS);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> currentState = objectMapper.convertValue(place, HashMap.class);
		partialUpdate.forEach((k, v) -> {
			if (currentState.containsKey(k)) {
				currentState.put(k, v);
			}
		});
		repository.save(objectMapper.convertValue(currentState, Place.class));
	}

	public void delete(Integer id) {

		Place place = findPlaceById(id);

		if (place.isApproved()
				&& !getRole().equals("ROLE_ADMIN")) {
			throw new BaseException(RestResponses.ACCESS_DENIED);
		}

		repository.deleteById(id);
	}

	private String getRole() {

		return SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::toString)
				.findFirst()
				.orElse("");
	}

	private Place findPlaceById(Integer id) {

		Optional<Place> optionalPlace = repository.findById(id);

		if (optionalPlace.isEmpty()) {
			throw new BaseException(RestResponses.NO_PLACE_FOUND);
		}

		return optionalPlace.get();
	}
}
