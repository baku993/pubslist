package com.alco.pubslist.security;

public enum RestResponses {

	MALFORMED_JSON(400, "Json is malformed"),
	MISSING_USERNAME_OR_PASSWORD(400, "Username or Password is not provided"),
	MISSING_PLACE_NAME_OR_ADDRESS(400, "Name or Address is not provided"),
	NO_PLACE_FOUND(406, "No place with following ID found"),
	AUTHENTICATION_FAILED(401, "Authentication failed for provided credentials"),
	AUTHORIZATION_FAILED(401, "Authorization failed"),
	USERNAME_IS_ALREADY_USED(400, "Username is already in use"),
	ACCESS_DENIED(403, "Access Forbidden"),
	AUTHENTICATION_SUCCESS(200, "Authentication Successful");

	private Integer statusCode;
	private String message;

	RestResponses(Integer statusCode, String message) {

		this.statusCode = statusCode;
		this.message = message;
	}

	public Integer getStatusCode() {

		return statusCode;
	}

	public String getMessage() {

		return message;
	}

}
