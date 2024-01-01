package com.signin.request.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signin.request.entity.Status;
import com.signin.request.entity.User;
import com.signin.request.repository.StatusRepository;
import com.signin.request.repository.UserRepository;
import com.signin.request.services.PendingServices;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PendingServicesImpl implements PendingServices {

	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public User pendingDetail(Long userId) {
		Optional<User> userDetails = userRepository.findById(userId);
		return userDetails.get();
	}

	@Override
	public List<User> getPendingDetails() {
		return userRepository.findAll();
	}

	@Override
	public int updateApprovalStatus(Long userId) {
		Integer isStatusUpdated = statusRepository.updateUserStatus("Approval", "It has approved", userId);
		return isStatusUpdated;
	}

	@Override
	public int updateDeniedStatus(Long userId, String statusMessage) {
		Integer isStatusUpdated = statusRepository.updateUserStatus("Denied", statusMessage, userId);
		return isStatusUpdated;
	}

}
