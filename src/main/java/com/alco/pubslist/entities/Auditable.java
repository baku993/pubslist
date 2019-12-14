package com.alco.pubslist.entities;

import com.alco.pubslist.configuration.UserContext;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable {

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private String updatedBy;

	@PrePersist
	public void setCreatedOn() {

		this.setCreatedOn(LocalDateTime.now());
		this.setCreatedBy(UserContext.getUsername());
	}

	@PreUpdate
	public void setUpdatedOn() {

		this.setUpdatedOn(LocalDateTime.now());
		this.setUpdatedBy(UserContext.getUsername());
	}

	public LocalDateTime getCreatedOn() {

		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {

		this.createdOn = createdOn;
	}

	public String getCreatedBy() {

		return createdBy;
	}

	public void setCreatedBy(String createdBy) {

		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedOn() {

		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {

		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {

		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {

		this.updatedBy = updatedBy;
	}
}