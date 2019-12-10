package com.alco.pubslist.entities;

import com.alco.pubslist.configuration.UserContext;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class Audit {

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@PrePersist
	public void setCreatedOn() {

		this.setCreatedOn(LocalDateTime.now());
		this.setCreatedBy(UserContext.getUserId());
	}

	@PreUpdate
	public void setUpdatedOn() {

		this.setUpdatedOn(LocalDateTime.now());
		this.setUpdatedBy(UserContext.getUserId());
	}

	public LocalDateTime getCreatedOn() {

		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {

		this.createdOn = createdOn;
	}

	public Integer getCreatedBy() {

		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {

		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedOn() {

		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {

		this.updatedOn = updatedOn;
	}

	public Integer getUpdatedBy() {

		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {

		this.updatedBy = updatedBy;
	}
}