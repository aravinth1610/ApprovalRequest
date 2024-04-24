package com.signin.request.services;

import java.util.List;
import java.util.Optional;

import com.signin.request.entity.User;
import com.signin.request.payload.request.OtherSignInRequestPayload;
import com.signin.request.payload.request.OtherSignUpRequestPayload;
import com.signin.request.payload.response.JustTestJoinQueryModal;
import com.signin.request.payload.response.JustTestNonRelationQuery;

public interface OtherUserServices {
	
	boolean createOtherNewUser(OtherSignUpRequestPayload newUser);
	User signinOtherUser(OtherSignInRequestPayload signinRequest);
	void testQuery();
	List<JustTestJoinQueryModal> testJoinQuery();
	List<JustTestNonRelationQuery> testJoinNonRelationQuery();

}
