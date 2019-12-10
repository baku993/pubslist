package com.alco.pubslist.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "comment")
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	private Place place;

	@Column(name = "user_id")
	private Integer userId;

	@Embedded
	private Audit audit = new Audit();

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getUserId() {

		return userId;
	}

	public void setUserId(Integer userId) {

		this.userId = userId;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public Place getPlace() {

		return place;
	}

	public void setPlace(Place place) {

		this.place = place;
	}

	public Audit getAudit() {

		return audit;
	}

	public void setAudit(Audit audit) {

		this.audit = audit;
	}

}
