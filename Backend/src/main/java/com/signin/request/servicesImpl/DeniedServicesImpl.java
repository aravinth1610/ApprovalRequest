package com.signin.request.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signin.request.entity.User;
import com.signin.request.repository.StatusRepository;
import com.signin.request.repository.UserRepository;
import com.signin.request.services.DeniedServices;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeniedServicesImpl implements DeniedServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StatusRepository statusRepository;

	@Override
	public List<User> getDeniedUserDetails() {
		return userRepository.findUserByStatus("Denied");
	}

	@Override
	public int updateApprovalStatus(Long userId, String statusMessage) {
		Integer isStatusUpdated = statusRepository.updateUserStatus("Approval", statusMessage, userId);
		return isStatusUpdated;
	}

	@Override
	public User deniedDetail(Long userId) {
		Optional<User> userDetails = userRepository.findById(userId);
		return userDetails.get();
	}
}
	
