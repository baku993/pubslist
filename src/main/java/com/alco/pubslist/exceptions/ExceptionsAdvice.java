package com.alco.pubslist.exceptions;

import com.alco.pubslist.security.RestResponses;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionsAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({BaseException.class})
	public ResponseEntity<Object> handleUserAlreadyExists(BaseException ex, WebRequest request) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return handleExceptionInternal(ex, formJsonBody(ex), headers,
				Objects.requireNonNull(HttpStatus.resolve(ex.getStatus())), request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		headers.add("Content-Type", "application/json");

		return handleExceptionInternal(ex, formJsonBody(new BaseException(RestResponses.MALFORMED_JSON)), headers,
				HttpStatus.BAD_REQUEST,
				request);
	}


	@ExceptionHandler({JpaSystemException.class})
	public ResponseEntity<Object> handlePSQLException(JpaSystemException ex, WebRequest request) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return handleExceptionInternal(ex, formJsonBody(ex), headers,
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private String formJsonBody(Exception exception) {

		JsonObject json = new JsonObject();
		json.addProperty("timestamp", new Timestamp(System.currentTimeMillis()).toString());
		json.addProperty("status", "error");
		json.addProperty("message", exception.getMessage());

		return json.toString();
	}

}
