package com.signin.request.token.services;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.signin.request.constant.AppConstant;
import com.signin.request.entity.AuthUser;
import com.signin.request.modal.TokenExceptionType;
import com.signin.request.securityConfig.UserPrincipal;

@Component
public class JWTTokenGenerator {

	@Value("${signInRequest.app.jwtGmailExpiration}")
	private Long jwtGmailExpiration;
	@Value("${signInRequest.app.jwtAuthExpiration}")
	private Long jwtAuthExpiration;
	@Value("${signInRequest.app.jwtExpiration}")
	private Long jwtExpiration;
	@Value("${signInRequest.app.jwtSecretKey}")
	private String JWTSecretKey;

	private Long TOKEN_VALIDITY;

	public String generatorToken(String type,UserPrincipal user) {

		if (type.equals(TokenExceptionType.GMAIL.name())) {
			this.TOKEN_VALIDITY = jwtGmailExpiration;
		} else if (type.equals(TokenExceptionType.JWT.name())) {
			this.TOKEN_VALIDITY = jwtExpiration;
		} else if (type.equals(TokenExceptionType.AUTH.name())) {
			this.TOKEN_VALIDITY = jwtAuthExpiration;
		}

		String[] authorities = populateAuthorities(user.getAuthorities());
	
		return JWT.create().withSubject(user.getGmail())
				.withArrayClaim(AppConstant.AUTHORITIES, authorities).withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.sign(Algorithm.HMAC256(JWTSecretKey.getBytes()));
	}

	private String[] populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return authoritiesSet.toArray(new String[0]);
	}

}
