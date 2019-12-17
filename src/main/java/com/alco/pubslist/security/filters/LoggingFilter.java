package com.alco.pubslist.security.filters;

import com.alco.pubslist.security.MultiReadServletRequestWrapper;
import org.apache.commons.io.IOUtils;
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

		MultiReadServletRequestWrapper requestWrapper = new MultiReadServletRequestWrapper(
				(HttpServletRequest) request);
		if (logger.isDebugEnabled()) {
			logRequest(requestWrapper);
		}
		chain.doFilter(requestWrapper, response);
	}

	private void logRequest(HttpServletRequest request) {

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
						headers.append("   ")
								.append(name)
								.append(":	")
								.append(request.getHeader(name))
								.append("\n"));
		try {
			msg.append(headers)
					.append("Body: \n")
					.append(IOUtils.toString(requestWrapper.getReader()));
		}
		catch (IOException ignore) {}
		logger.debug(msg);
	}

}
