package com.alco.pubslist.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "places")
public class Place {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;
	private String name;
	private String address;
	private String latitude;
	private String longitude;

	@Column(name = "owner_id")
	private Integer ownerId;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.DELETE)
	@JoinColumn(name = "place_id")
	private List<Comment> comments;

	private boolean approved;

	private boolean enabled;

	@Embedded
	private Audit audit = new Audit();

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

	public String getLatitude() {

		return latitude;
	}

	public void setLatitude(String latitude) {

		this.latitude = latitude;
	}

	public String getLongitude() {

		return longitude;
	}

	public void setLongitude(String longitude) {

		this.longitude = longitude;
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

	public Integer getOwnerId() {

		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {

		this.ownerId = ownerId;
	}

	public boolean isPlaceOwnedByUser(String userId) {

		return getOwnerId().toString().equals(userId);
	}

	public Audit getAudit() {

		return audit;
	}

	public void setAudit(Audit audit) {

		this.audit = audit;
	}

}
