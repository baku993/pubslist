package com.alco.pubslist.security.filters;

import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

public class LoggingFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		logRequest((HttpServletRequest) request);
		chain.doFilter(request, response);
	}

	protected void logRequest(HttpServletRequest request) {

		logger.isDebugEnabled();
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		StringBuffer msg = new StringBuffer().append("\n");

		msg.append("Path:	")
				.append(request.getServletPath())
				.append("\nMethod:	")
				.append(request.getMethod())
				.append("\nHeaders:\n");
		StringBuffer headers = new StringBuffer();
		Collections.list(request.getHeaderNames())
				.forEach(name ->
						headers.append(name)
								.append(":	")
								.append(request.getHeader(name))
								.append("\n"));
		msg.append(headers)
				.append("Body: \n")
				.append(new String(requestWrapper.getContentAsByteArray()));
		logger.debug(msg);
	}

}
