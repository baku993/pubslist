package com.alco.pubslist.security;

public enum RestResponses {

	MALFORMED_JSON(400, "Json is malformed"),
	MISSING_USERNAME_OR_PASSWORD(400, "Username or Password is not provided"),
	AUTHENTICATION_FAILED(401, "Authentication failed for provided credentials"),
	AUTHORIZATION_FAILED(401, "Authorization failed"),
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
