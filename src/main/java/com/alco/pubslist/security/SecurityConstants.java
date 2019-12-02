package com.alco.pubslist.security;

public final class SecurityConstants {

	public static final String LOGIN_URL = "/login";
	public static final String SING_UP_URL = "/users";

	public static final String JWT_SECRET = "@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&";

	// JWT token defaults
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";

	private SecurityConstants() {

		throw new IllegalStateException("Cannot create instance of static util class");
	}
}
