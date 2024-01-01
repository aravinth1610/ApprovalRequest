package com.signin.request.services;

import java.util.List;

import com.signin.request.entity.User;

public interface DeniedServices {

	List<User> getDeniedUserDetails();
	int updateApprovalStatus(Long userId, String statusMessage);
	User deniedDetail(Long userId);
}
