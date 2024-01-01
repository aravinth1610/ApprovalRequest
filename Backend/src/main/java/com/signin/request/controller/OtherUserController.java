package com.signin.request.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.signin.request.entity.Status;
import com.signin.request.entity.User;
import com.signin.request.payload.request.AuthsignUpRequest;
import com.signin.request.payload.request.OtherSignInRequestPayload;
import com.signin.request.payload.request.OtherSignUpRequestPayload;
import com.signin.request.services.OtherUserServices;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/signinrequest")
public class OtherUserController {

	@Autowired
	private OtherUserServices otherUserServices;

	@PostMapping("/other-app/signup")
	private final ResponseEntity<?> createNewUser(@RequestBody @Valid OtherSignUpRequestPayload otherNewuser) {
		boolean isCreateNewUser = otherUserServices.createOtherNewUser(otherNewuser);
		return ((isCreateNewUser) ? new ResponseEntity<Integer>(HttpStatus.CREATED)
				: new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@PostMapping("/other-app/login")
	private final ResponseEntity<?> createNewUser(@RequestBody @Valid OtherSignInRequestPayload otherSignin) {
		User user = otherUserServices.signinOtherUser(otherSignin);

		if (user != null) {
			if (user.getStatus().getStatus().equals("Pending")) {
				Map<String, String> ValidatorError = new LinkedHashMap<String, String>();
				ValidatorError.put("validationErrors", "User status is Pending");
				return new ResponseEntity<Map<String, String>>(ValidatorError, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Integer>(HttpStatus.OK);
		} else {

			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}

	}

}
