package com.alco.pubslist.controller

import com.alco.pubslist.entities.Comment
import com.alco.pubslist.security.SecurityConstants
import com.alco.pubslist.services.CommentsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestController
class CommentsController {
	@Autowired
	private val service: CommentsService? = null

	@GetMapping(SecurityConstants.COMMENTS_URL + "/{id}")
	fun allCommentsForPlace(@PathVariable("id") id: Int?): Iterable<Comment> {
		return service!!.findAllCommentsOnPlace(id)
	}

	@PostMapping(SecurityConstants.COMMENTS_URL)
	fun save(@RequestBody newComment: Comment?): Comment {
		return service!!.addComment(newComment)
	}

	@PatchMapping(SecurityConstants.COMMENTS_URL + "/{id}")
	@Throws(IOException::class)
	fun partialUpdate(@PathVariable("id") id: Int?, request: HttpServletRequest): ResponseEntity<*> {
		service!!.edit(request.reader, id)
		return ResponseEntity.ok("")
	}

	@DeleteMapping(SecurityConstants.COMMENTS_URL + "/{id}")
	fun delete(@PathVariable("id") id: Int?): ResponseEntity<*> {
		service!!.delete(id)
		return ResponseEntity.ok("")
	}
}
