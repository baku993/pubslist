package com.alco.pubslist.security;

import com.alco.pubslist.Helper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(
			HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException exception) throws IOException {

		Helper.createErrorResponse(response, 403, SecurityConstants.ACCESS_DENIED);
	}
}
