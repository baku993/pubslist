package com.alco.pubslist.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "places")
class Place : Auditable() {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	var id: Int? = null

	var name: String? = null

	var url: String? = null

	var votes: Int? = null

	var description: String? = null

	var image: String? = null

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade(value = [CascadeType.DELETE])
	@JoinColumn(name = "place_id")
	val comments: List<Comment>? = null

	@Column(name = "approved")
	var isApproved = false

	@Column(name = "disabled")
	var isDisabled = false

	fun isPlaceOwnedByUser(username: String): Boolean {
		return createdBy == username
	}

}
