package com.signin.request.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signin.request.entity.User;
import com.signin.request.repository.StatusRepository;
import com.signin.request.repository.UserRepository;
import com.signin.request.services.ApprovalServices;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApprovalServicesImpl implements ApprovalServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StatusRepository statusRepository;

	@Override
	public List<User> getApprovalUserDetails() {
		return userRepository.findUserByStatus("Approval");
	}

	@Override
	public int updateDeniedStatus(Long userId, String statusMessage) {
		Integer isStatusUpdated = statusRepository.updateUserStatus("Denied", statusMessage, userId);
		return isStatusUpdated;
	}

	@Override
	public User approvalDetail(Long userId) {
		Optional<User> userDetails = userRepository.findById(userId);
		return userDetails.get();
	}
}
