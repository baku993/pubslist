package com.alco.pubslist.security.filters;

import com.alco.pubslist.Helper;
import com.alco.pubslist.security.RestResponses;
import com.alco.pubslist.security.SecurityConstants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private AuthenticationManager authenticationManager;
	private Long expirationTime;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, Long expirationTime) {

		super(SecurityConstants.LOGIN_URL);
		this.authenticationManager = authenticationManager;
		this.expirationTime = expirationTime;

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		try {

			JsonObject json = JsonParser.parseReader(request.getReader()).getAsJsonObject();
			String username = json.getAsJsonPrimitive("username").getAsString();
			String password = json.getAsJsonPrimitive("password").getAsString();

			if (username.isEmpty() || password.isEmpty()) {
				Helper.formResponse(response, RestResponses.MISSING_USERNAME_OR_PASSWORD);
				return null;
			}
			AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					password);
			return authenticationManager.authenticate(authenticationToken);

		}
		catch (NullPointerException e) {
			Helper.formResponse(response, RestResponses.MISSING_USERNAME_OR_PASSWORD);
		}
		catch (MalformedJsonException | JsonSyntaxException e) {
			Helper.formResponse(response, RestResponses.MALFORMED_JSON);
		}

		return null;
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {

		Helper.formResponse(response, RestResponses.AUTHENTICATION_FAILED);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication) throws IOException {

		List<String> roles = authentication.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

		String token = Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
				.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
				.setIssuer(SecurityConstants.TOKEN_ISSUER)
				.setAudience(SecurityConstants.TOKEN_AUDIENCE)
				.setSubject(authentication.getName())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.claim("role", roles)
				.compact();

		Helper.formResponse(response, RestResponses.AUTHENTICATION_SUCCESS, token);
	}
}
