package com.alco.pubslist.exceptions;

import com.alco.pubslist.security.RestResponses;

public class BaseException extends RuntimeException {

	private final Integer statusCode;

	public BaseException(RestResponses restResponse) {

		super(restResponse.getMessage());
		this.statusCode = restResponse.getStatusCode();
	}

	Integer getStatus() {

		return statusCode;
	}

}
