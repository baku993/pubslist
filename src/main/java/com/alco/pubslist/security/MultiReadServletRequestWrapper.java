package com.alco.pubslist.security;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MultiReadServletRequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;

	public MultiReadServletRequestWrapper(HttpServletRequest httpServletRequest) throws IOException {

		super(httpServletRequest);
		InputStream requestInputStream = httpServletRequest.getInputStream();
		this.body = StreamUtils.copyToByteArray(requestInputStream);
	}

	@Override
	public BufferedReader getReader() {

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
		return new BufferedReader(new InputStreamReader(byteArrayInputStream));
	}

	@Override
	public ServletInputStream getInputStream() {
		return new CachedBodyServletInputStream(this.body);
	}
}
