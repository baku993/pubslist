package com.alco.pubslist.controller

import com.alco.pubslist.configuration.UserContext
import com.alco.pubslist.entities.User
import com.alco.pubslist.security.SecurityConstants
import com.alco.pubslist.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestController
class UsersController {
	@Autowired
	private val userService: UserService? = null

	@Secured("ROLE_ADMIN")
	@GetMapping(SecurityConstants.USERS_URL)
	@ResponseBody
	fun all(): Iterable<User> {
		return userService!!.findAll()
	}

	@Secured("ROLE_ADMIN")
	@ResponseBody
	@GetMapping(SecurityConstants.USER_URL + "/{id}")
	fun findById(@PathVariable("id") id: Int?): User {
		return userService!!.findById(id)
	}

	@get:ResponseBody
	@get:GetMapping(SecurityConstants.USER_URL)
	val currentUser: User
		get() = userService!!.findById(UserContext.getUserId())

	@PatchMapping(SecurityConstants.USERS_URL + "/{id}")
	@Throws(IOException::class)
	fun partialUpdate(@PathVariable("id") id: Int?, request: HttpServletRequest): ResponseEntity<*> {
		userService!!.update(request.reader, id)
		return ResponseEntity.ok("")
	}

	@PostMapping(SecurityConstants.SIGN_UP_URL)
	fun save(@RequestBody newUser: User?): ResponseEntity<*> {
		userService!!.save(newUser)
		return ResponseEntity.ok("")
	}
}
