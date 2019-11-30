package com.alco.pubslist;

import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;

public class Helper {

	public static String cacheData(String data, String salt) {

		return DigestUtils.sha512Hex(data + salt);
	}

	public static void createErrorResponse(ServletResponse response,
			Integer statusCode,
			String message) throws IOException {

		HttpServletResponse mappedResponse = (HttpServletResponse) response;
		mappedResponse.setStatus(statusCode);
		mappedResponse.setContentType("application/json");
		mappedResponse.setCharacterEncoding("UTF-8");

		JsonObject json = new JsonObject();
		json.addProperty("timestamp", new Timestamp(System.currentTimeMillis()).toString());
		json.addProperty("status", "error");
		json.addProperty("message", message);

		Writer writer = mappedResponse.getWriter();
		writer.write(json.toString());
		writer.flush();
		writer.close();
	}

	public static void createAuthenticationSuccessResponse(ServletResponse response,
			Integer statusCode,
			String token) throws IOException {

		HttpServletResponse mappedResponse = (HttpServletResponse) response;
		mappedResponse.setStatus(statusCode);
		mappedResponse.setContentType("application/json");
		mappedResponse.setCharacterEncoding("UTF-8");

		JsonObject json = new JsonObject();
		json.addProperty("timestamp", new Timestamp(System.currentTimeMillis()).toString());
		json.addProperty("status", "successful");
		json.addProperty("token", token);

		Writer writer = mappedResponse.getWriter();
		writer.write(json.toString());
		writer.flush();
		writer.close();
	}
}
