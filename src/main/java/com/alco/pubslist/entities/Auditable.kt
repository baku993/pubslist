package com.alco.pubslist.entities

import com.alco.pubslist.configuration.UserContext
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class Auditable {

	@Column(name = "created_on")
	var createdOn: LocalDateTime? = null

	@Column(name = "created_by")
	var createdBy: String? = null

	@Column(name = "updated_on")
	var updatedOn: LocalDateTime? = null

	@Column(name = "updated_by")
	var updatedBy: String? = null

	@PrePersist
	fun setCreatedOn() {
		createdOn = LocalDateTime.now()
		createdBy = UserContext.username
	}

	@PreUpdate
	fun setUpdatedOn() {
		updatedOn = LocalDateTime.now()
		updatedBy = UserContext.username
	}

}
