package com.alco.pubslist.services;

import com.alco.pubslist.configuration.UserContext;
import com.alco.pubslist.entities.Place;
import com.alco.pubslist.entities.Roll;
import com.alco.pubslist.repository.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class RollService {

	public static final String SYSTEM_NAME = "system";
	private Roll rollOfTheDay;

	private final static SecureRandom SECURE_RANDOM = new SecureRandom();

	@Autowired
	private PlaceService placeService;

	@Autowired
	private RollRepository repository;

	public Roll rollOfTheDay() {

		if (rollOfTheDay == null
				|| !rollOfTheDay.isRolledToday()) {
			updateRollOfToday();
			return rollOfTheDay;
		}

		return rollOfTheDay;
	}

	public Roll manualRoll() {

		Place rolledPlace = getRandomPlace();
		return repository.save(new Roll(rolledPlace, UserContext.getUsername()));

	}

	private Place getRandomPlace() {

		List<Place> approvedPlaces = StreamSupport
				.stream(placeService.findAll().spliterator(), false)
				.filter(Place::isApproved)
				.collect(Collectors.toList());
		return approvedPlaces.get(SECURE_RANDOM.nextInt(approvedPlaces.size()));
	}

	private void updateRollOfToday() {

		Optional<Roll> rollOptional = repository.findFirstByRolledByOrderByRolledAtDesc(SYSTEM_NAME);

		if (rollOptional.isPresent() && rollOptional.get().isRolledToday()) {
			rollOfTheDay = rollOptional.get();
		}
		else {
			Place rolledPlace = getRandomPlace();
			rollOfTheDay = repository.save(new Roll(rolledPlace, SYSTEM_NAME));
		}
	}
}
