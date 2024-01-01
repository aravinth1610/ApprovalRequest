package com.signin.request.services;

import org.springframework.http.ResponseEntity;

import com.signin.request.payload.request.AuthsignInRequest;
import com.signin.request.payload.request.AuthsignUpRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthUserServices {

	boolean createAuthNewUser(AuthsignUpRequest newUser);
	boolean alreadyEmailIsExists(String gmail);
	String generateRefressToken(HttpServletRequest request);
	
	
}