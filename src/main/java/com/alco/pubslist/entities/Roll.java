package com.alco.pubslist.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "rolls")
public class Roll {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "place_id")
	private Place place;

	LocalDateTime rolledAt;

	String rolledBy;

	public Roll() {

	}

	public Roll(Place place, String rolledBy) {

		this.place = place;
		this.rolledAt = LocalDateTime.now();
		this.rolledBy = rolledBy;
	}

	public boolean isRolledToday() {

		return this.rolledAt.toLocalDate().equals(LocalDate.now());
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Place getPlace() {

		return place;
	}

	public void setPlace(Place place) {

		this.place = place;
	}

	public LocalDateTime getRolledAt() {

		return rolledAt;
	}

	public void setRolledAt(LocalDateTime rolledAt) {

		this.rolledAt = rolledAt;
	}

	public String getRolledBy() {

		return rolledBy;
	}

	public void setRolledBy(String rolledBy) {

		this.rolledBy = rolledBy;
	}
}
