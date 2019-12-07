package com.alco.pubslist.controller;

import com.alco.pubslist.entities.Place;
import com.alco.pubslist.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.alco.pubslist.security.SecurityConstants.PLACES_URL;

@RestController
public class PlaceController {

	@Autowired
	private PlaceService service;

	@GetMapping(PLACES_URL)
	Iterable<Place> all() {

		return service.findAll();
	}

	@PostMapping("PLACES_URL")
	Place save(@RequestBody Place newPlace) {

		return service.suggestPlace(newPlace);
	}

	@PatchMapping(PLACES_URL + "/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable("id") Integer id, HttpServletRequest request)
			throws IOException {

		service.update(request.getReader(), id);
		return ResponseEntity.ok("Place updated");
	}

	@DeleteMapping(PLACES_URL + "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

		service.delete(id);
		return ResponseEntity.ok("Place updated");
	}

}
