package com.alco.pubslist.entities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "users")
class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	var id: Int? = null

	@Column(name = "username")
	var username: String? = null

	@Column(name = "name")
	var name: String? = null

	@Column(name = "surname")
	var surname: String? = null

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	var role = UserRole.USER

	@Column
	var isDisabled = false

	@Column
	var image: String? = null

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	var password: String? = null

}