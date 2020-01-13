package com.alco.pubslist.entities

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "rolls")
class Roll {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	var id: Int? = null

	@ManyToOne
	@JoinColumn(name = "place_id")
	var place: Place? = null

	@Column(name = "rolled_at")
	var rolledAt: LocalDateTime? = null

	@Column(name = "rolled_by")
	var rolledBy: String? = null

	constructor()

	constructor(place: Place?, rolledBy: String?) {
		this.place = place
		rolledAt = LocalDateTime.now()
		this.rolledBy = rolledBy
	}

	val isRolledToday: Boolean
		get() = rolledAt!!.toLocalDate() == LocalDate.now()

}
