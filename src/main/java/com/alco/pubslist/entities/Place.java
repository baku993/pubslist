package com.alco.pubslist.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "places")
public class Place {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;
	private String name;
	private String address;
	private String coordinate_1;
	private String coordinate_2;

	private boolean approved;

	private boolean enabled;

	public Place() {

	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getCoordinate_1() {

		return coordinate_1;
	}

	public void setCoordinate_1(String coordinate_1) {

		this.coordinate_1 = coordinate_1;
	}

	public String getCoordinate_2() {

		return coordinate_2;
	}

	public void setCoordinate_2(String coordinate_2) {

		this.coordinate_2 = coordinate_2;
	}

	public boolean isApproved() {

		return approved;
	}

	public void setApproved(boolean approved) {

		this.approved = approved;
	}

	public boolean isEnabled() {

		return enabled;
	}

	public void setEnabled(boolean enabled) {

		this.enabled = enabled;
	}
}
