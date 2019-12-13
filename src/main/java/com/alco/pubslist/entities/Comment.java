package com.alco.pubslist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "comments")
public class Comment extends Auditable {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;
	private String text;

	@Column(name = "place_id")
	private Integer placeId;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public Integer getPlaceId() {

		return placeId;
	}

	public void setPlaceId(Integer placeId) {

		this.placeId = placeId;
	}

	public boolean isCommentWrittenByUser(Integer userId) {

		return getCreatedBy().equals(userId);
	}
}
