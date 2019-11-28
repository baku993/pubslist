package com.alco.pubslist.controller;

import com.alco.pubslist.entities.Place;
import com.alco.pubslist.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  PlaceController {

    @Autowired
    PlaceRepository repository;

    @GetMapping("/places")
    Iterable<Place> all() {
        return repository.findAll();
    }

    @PostMapping("/places")
    Place save(@RequestBody Place newPlace) {
        return repository.save(newPlace);
    }
}
