package com.signin.request.token.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.signin.request.constant.AppConstant;

import io.micrometer.common.util.StringUtils;

@Component
public class JWTTokenValidation {

	@Value("${signInRequest.app.jwtSecretKey}")
	private String JWTSecretKey;

	public String getClaimNameToken(String token) {
		JWTVerifier jwtVerifier = getJwtVerifier();
		return jwtVerifier.verify(token).getSubject();
		}

	public Boolean isTokenValid(String email, String token) {
		JWTVerifier jwtVerifier = getJwtVerifier();
		return StringUtils.isNotEmpty(email) && !isTokenExpired(jwtVerifier, token);
	}

	public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
		String[] claims = getClaimsFromToken(token);
		return Arrays.stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	private Boolean isTokenExpired(JWTVerifier jwtVerifier, String token) {
		Date expiration = jwtVerifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}

	private String[] getClaimsFromToken(String token) {
		JWTVerifier jwtVerifier = getJwtVerifier();
		return jwtVerifier.verify(token).getClaim(AppConstant.AUTHORITIES).asArray(String.class);
	}

	private JWTVerifier getJwtVerifier() {
		JWTVerifier jwtVerifier;
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWTSecretKey);
			jwtVerifier = JWT.require(algorithm).build();
		} catch (JWTVerificationException e) {
			throw new JWTVerificationException(AppConstant.TOKEN_UNVERIFIABLE);
		}
		return jwtVerifier;
	}
}
