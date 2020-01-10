package com.alco.pubslist.entities

import javax.persistence.*

@Entity
@Table(name = "comments")
class Comment : Auditable() {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	var id: Int? = null

	var text: String? = null
	@Column(name = "place_id")
	var placeId: Int? = null

	fun isCommentWrittenByUser(username: String): Boolean {
		return createdBy == username
	}
}