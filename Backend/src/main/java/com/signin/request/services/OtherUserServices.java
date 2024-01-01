package com.signin.request.services;

import java.util.Optional;

import com.signin.request.entity.User;
import com.signin.request.payload.request.OtherSignInRequestPayload;
import com.signin.request.payload.request.OtherSignUpRequestPayload;

public interface OtherUserServices {
	
	boolean createOtherNewUser(OtherSignUpRequestPayload newUser);
	User signinOtherUser(OtherSignInRequestPayload signinRequest);

}
