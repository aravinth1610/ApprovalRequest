package com.signin.request.servicesImpl;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.signin.request.entity.Status;
import com.signin.request.entity.User;
import com.signin.request.payload.request.OtherSignInRequestPayload;
import com.signin.request.payload.request.OtherSignUpRequestPayload;
import com.signin.request.repository.UserRepository;
import com.signin.request.services.OtherUserServices;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OtherUserServicesImpl implements OtherUserServices {

	@Autowired
	private UserRepository otherUserRepository;

	@Override
	public boolean createOtherNewUser(OtherSignUpRequestPayload otherUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setGmail(otherUser.getGmail());
		user.setPassword(encoder.encode(otherUser.getPassword()));
		user.setName(otherUser.getName());
		user.setPhoneNo(otherUser.getPhoneNo());
		user.setCompanyName(otherUser.getCompanyName());
		user.setUpdateOn(new Date(0));
		user.setCreatedOn(new Date(0));
		
		Status status = new Status();
		//status.setStatus("Pending");
		//status.setStatusMessage(otherUser.getStatus().getStatusMessage());
		status.setUser(user);

		user.setStatus(status);
		otherUserRepository.save(user);

		return true;
	}

	@Override
	public User signinOtherUser(OtherSignInRequestPayload signinRequest) {
		User otherUser = otherUserRepository.findByGmail(signinRequest.getGmail());
		return otherUser;
	}

}
