package com.alco.pubslist.repository

import com.alco.pubslist.entities.Comment
import com.alco.pubslist.entities.Place
import com.alco.pubslist.entities.Roll
import com.alco.pubslist.entities.User
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * @author victor
 * @date 10.01.2020
 */

interface RollRepository : CrudRepository<Roll?, Int?> {
	fun findFirstByRolledByOrderByRolledAtDesc(username: String?): Optional<Roll?>
}

interface UserRepository : CrudRepository<User?, Int?> {
	fun findDistinctFirstByUsername(username: String?): User?
	fun existsByUsername(username: String?): Boolean
}

interface PlaceRepository : CrudRepository<Place?, Int?>

interface CommentRepository : CrudRepository<Comment?, Int?> {
	fun findCommentsByPlaceId(placeId: Int?): List<Comment?>?
}
