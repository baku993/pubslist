package com.alco.pubslist.controller;

import com.alco.pubslist.entities.Roll;
import com.alco.pubslist.services.RollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alco.pubslist.security.SecurityConstants.ROLLS_URL;

@RestController
public class RollsController {

	@Autowired
	private RollService service;

	@GetMapping(ROLLS_URL + "/daily")
	public Roll getDailyRoll() {

		return service.rollOfTheDay();
	}

	@GetMapping(ROLLS_URL + "/manual")
	public Roll getManualRoll() {

		return service.manualRoll();
	}
}
