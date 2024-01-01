package com.signin.request.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signin.request.constant.AppConstant;
import com.signin.request.constant.Properties;
import com.signin.request.entity.AuthUser;
import com.signin.request.modal.TokenExceptionType;
import com.signin.request.payload.request.AuthsignInRequest;
import com.signin.request.payload.request.AuthsignUpRequest;
import com.signin.request.repository.AuthUserRepository;
import com.signin.request.securityConfig.UserPrincipal;
import com.signin.request.services.AuthUserServices;
import com.signin.request.token.services.JWTCookiesServices;
import com.signin.request.token.services.JWTTokenGenerator;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/signinrequest/authentication")
public class AuthenticationController {

	@Value("${signInRequest.app.jwtCookieName}")
	private String JWTCookiesName;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AuthUserServices authUserServices;
	@Autowired
	private JWTTokenGenerator jwtTokenGenerator;
	@Autowired
	private JWTCookiesServices jwtCookiesServices;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	/**
	 * 
	 * Should not put plain text in body it will make errorResponse in frontend the
	 * body need to be Object or map
	 * 
	 */

	@PostMapping("/auth/signup")
	private final ResponseEntity<?> createNewUser(@RequestBody @Valid AuthsignUpRequest user) {
		System.out.println(user);

		boolean isCreateNewUser = authUserServices.createAuthNewUser(user);
		return ((isCreateNewUser) ? new ResponseEntity<Integer>(HttpStatus.CREATED)
				: new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@GetMapping("/auth/isexists-email")
	private final ResponseEntity<?> alreadyEmailIsExists(@RequestParam(value = "gmail") String gmail) {
		boolean isEmailExists = authUserServices.alreadyEmailIsExists(gmail);
		return ((isEmailExists) ? new ResponseEntity<Boolean>(isEmailExists, HttpStatus.OK)
				: new ResponseEntity<Boolean>(isEmailExists, HttpStatus.OK));
	}

	@PostMapping("/auth/login")
	private final ResponseEntity<?> login(@RequestBody @Valid AuthsignInRequest user) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getGmail(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal authUser = (UserPrincipal) authentication.getPrincipal();

		if (encoder.matches(user.getPassword(), authUser.getPassword())) {

			String jwt = jwtTokenGenerator.generatorToken(TokenExceptionType.JWT.name(), authUser);
			String refreshJwtToken = jwtCookiesServices.createRefreshJWTToken(authUser.getGmail());

			ResponseCookie jwtCookie = jwtCookiesServices.setJwtTokenInCookie(JWTCookiesName, refreshJwtToken, "/");
			Map<String, Object> loginSuccess = new LinkedHashMap<String, Object>();
			loginSuccess.put("successMessage", "success");
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.AUTHORIZATION, jwt);
			httpHeaders.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt)
					.header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(loginSuccess);
		} else {
			return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/auth/refress-token")
	private final ResponseEntity<?> generatedJwtToken(HttpServletRequest request) {
		String isGeneratedeToken = authUserServices.generateRefressToken(request);
		System.out.println(isGeneratedeToken);
		if (!isGeneratedeToken.equals("UNAUTHORIZED")) {
			Map<String, Object> refressSuccess = new LinkedHashMap<String, Object>();
			refressSuccess.put("successMessage", "success");

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.AUTHORIZATION, isGeneratedeToken);
			return new ResponseEntity<Map<String, Object>>(refressSuccess, httpHeaders, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		System.out.println("lo");
		ResponseCookie refreshTokenCookie = jwtCookiesServices.cleanJwtTokenInCookie(JWTCookiesName, "/");
		System.out.println(refreshTokenCookie);
	
		Map<String, Object> refressSuccess = new LinkedHashMap<String, Object>();
		refressSuccess.put("successMessage", "success");
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
		return new ResponseEntity<Map<String, Object>>(refressSuccess, httpHeaders, HttpStatus.OK);
		//return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).build();
	}

}
