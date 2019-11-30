package com.alco.pubslist.security;

public final class SecurityConstants {

	public static final String LOGIN_URL = "/login";
	public static final String SING_UP_URL = "/users";
	// Signing key for HS512 algorithm
	// You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
	public static final String JWT_SECRET = "@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&";

	// JWT token defaults
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";

	// Error messages
	public static final String MALFORMED_JSON = "Json is malformed";
	public static final String MISSING_USERNAME_OR_PASSWORD = "Username or Password is not provided";
	public static final String AUTHENTICATION_FAILED = "Authentication failed for provided credentials";
	public static final String AUTHORIZATION_FAILED = "Authorization failed";
	public static final String ACCESS_DENIED = "Access Forbidden";

	private SecurityConstants() {

		throw new IllegalStateException("Cannot create instance of static util class");
	}
}
