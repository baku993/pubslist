package com.alco.pubslist.repository;

import com.alco.pubslist.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

	List<Comment> findCommentsByPlaceId(Integer placeId);
}
