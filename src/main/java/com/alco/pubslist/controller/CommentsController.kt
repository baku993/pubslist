package com.alco.pubslist.controller;

import com.alco.pubslist.entities.Comment;
import com.alco.pubslist.services.CommentsService;
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

import static com.alco.pubslist.security.SecurityConstants.COMMENTS_URL;

@RestController
public class CommentsController {

	@Autowired
	private CommentsService service;

	@GetMapping(COMMENTS_URL + "/{id}")
	public Iterable<Comment> allCommentsForPlace(@PathVariable("id") Integer id) {

		return service.findAllCommentsOnPlace(id);
	}

	@PostMapping(COMMENTS_URL)
	Comment save(@RequestBody Comment newComment) {

		return service.addComment(newComment);
	}

	@PatchMapping(COMMENTS_URL + "/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable("id") Integer id, HttpServletRequest request)
			throws IOException {

		service.edit(request.getReader(), id);
		return ResponseEntity.ok("");
	}

	@DeleteMapping(COMMENTS_URL + "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

		service.delete(id);
		return ResponseEntity.ok("");
	}

}
