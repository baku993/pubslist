package com.alco.pubslist.controller

import com.alco.pubslist.entities.Roll
import com.alco.pubslist.security.SecurityConstants
import com.alco.pubslist.services.RollService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RollsController {
	@Autowired
	private val service: RollService? = null

	@get:GetMapping(SecurityConstants.ROLLS_URL + "/daily")
	val dailyRoll: Roll
		get() = service!!.rollOfTheDay()

	@get:GetMapping(SecurityConstants.ROLLS_URL + "/manual")
	val manualRoll: Roll
		get() = service!!.manualRoll()

	@GetMapping(SecurityConstants.ROLLS_URL)
	fun all(): Iterable<Roll> {
		return service!!.findAll()
	}
}
