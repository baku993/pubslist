package com.alco.pubslist.controller

import com.alco.pubslist.entities.Place
import com.alco.pubslist.security.SecurityConstants
import com.alco.pubslist.services.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestController
class PlaceController {
	@Autowired
	private val service: PlaceService? = null

	@GetMapping(SecurityConstants.PLACES_URL)
	fun all(): Iterable<Place> {
		return service!!.findAll()
	}

	@GetMapping(SecurityConstants.PLACES_URL + "/{id}")
	fun getById(@PathVariable("id") id: Int?): Place {
		return service!!.findById(id)
	}

	@PostMapping(SecurityConstants.PLACES_URL)
	fun save(@RequestBody newPlace: Place?): Place {
		return service!!.suggestPlace(newPlace)
	}

	@PatchMapping(SecurityConstants.PLACES_URL + "/{id}")
	@Throws(IOException::class)
	fun partialUpdate(@PathVariable("id") id: Int?, request: HttpServletRequest): ResponseEntity<*> {
		service!!.update(request.reader, id)
		return ResponseEntity.ok("")
	}

	@DeleteMapping(SecurityConstants.PLACES_URL + "/{id}")
	fun delete(@PathVariable("id") id: Int?): ResponseEntity<*> {
		service!!.delete(id)
		return ResponseEntity.ok("")
	}
}
