package com.signin.request.token.services;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.signin.request.constant.AppConstant;
import com.signin.request.entity.AuthUser;
import com.signin.request.modal.TokenExceptionType;
import com.signin.request.repository.AuthUserRepository;
import com.signin.request.securityConfig.UserDetailServiceImpl;
import com.signin.request.securityConfig.UserPrincipal;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTCookiesServices {

	@Value("${signInRequest.app.jwtSecretKey}")
	private String JWTSecretKey;
	@Value("${signInRequest.app.jwtRefreshExpiration}")
	private Long jwtRefreshExpiration;

	@Autowired
	private JWTTokenValidation jwtTokenValidation;
	@Autowired
	private JWTTokenGenerator jwtTokenGenerator;
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	public String createRefreshJWTToken(String gmail) {
		String JwtTokenId = Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
		return JWT.create().withSubject(gmail).withClaim("token", JwtTokenId)
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withExpiresAt(new Date(System.currentTimeMillis() + jwtRefreshExpiration * 1000))
				.sign(Algorithm.HMAC256(JWTSecretKey.getBytes()));

	}

	public boolean validateRefreshToken(String jwt) {
		String refreshJwtTokenClaimName = jwtTokenValidation.getClaimNameToken(jwt);
		if (jwtTokenValidation.isTokenValid(refreshJwtTokenClaimName, jwt)) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(refreshJwtTokenClaimName);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return true;
		} else {
			return false;
		}

	}

	public String GenerateForHeaderToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal authUser = (UserPrincipal) authentication.getPrincipal();
		String jwt = jwtTokenGenerator.generatorToken(TokenExceptionType.JWT.name(), authUser);
		return jwt;
	}

	public ResponseCookie setJwtTokenInCookie(String name, String value, String path) {
		return ResponseCookie.from(name, value).path(path).maxAge(24 * 60 * 60) // 24 hours
				.httpOnly(true).secure(true).sameSite("Strict").build();
	}

	public String getJwtFromCookies(HttpServletRequest request, String name) {
		System.out.println(request + "" + name);
		Cookie cookie = WebUtils.getCookie(request, name);
		System.out.println(cookie + "cok");
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

	public ResponseCookie cleanJwtTokenInCookie(String name, String path) {
		return ResponseCookie.from(name, "").path(path).maxAge(0).httpOnly(true).secure(true).sameSite("Strict")
				.build();
	}

}
