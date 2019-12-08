package com.alco.pubslist.entities;

/**
 * @author victor
 * @date 09.12.2019
 */
public enum UserRole {
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	private String roleName;

	UserRole(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {

		return roleName;
	}
}
