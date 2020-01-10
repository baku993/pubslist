package com.alco.pubslist.configuration

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Contains helper methods related to current user
 *
 * @author victor
 * @date 07.12.2019
 */
object UserContext {
	/**
	 * Checks if user is admin
	 */
	@JvmStatic
	val isAdmin: Boolean
		get() = SecurityContextHolder
				.getContext()
				.authentication
				.authorities
				.stream()
				.anyMatch { r: GrantedAuthority? -> r!!.authority == "ROLE_ADMIN" }

	/**
	 * Can be used to get current user id
	 */
	@JvmStatic
	val userId: Int
		get() = SecurityContextHolder.getContext().authentication.details.toString().toInt()

	@JvmStatic
	val username: String
		get() = SecurityContextHolder.getContext().authentication.name
}
