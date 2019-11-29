package com.alco.pubslist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "role")
	private String role;

	@Column(name = "password")
	private String password;

	User() {

	}

	public void setId(Integer id) {

		this.id = id;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setSurname(String surname) {

		this.surname = surname;
	}

	public void setRole(String role) {

		this.role = role;
	}

	public Integer getId() {

		return id;
	}

	public String getName() {

		return name;
	}

	public String getSurname() {

		return surname;
	}

	public String getRole() {

		return role;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}
}
