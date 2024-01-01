package com.signin.request.services;

import java.util.List;
import java.util.Optional;

import com.signin.request.entity.User;

public interface ApprovalServices {

	public List<User> getApprovalUserDetails();
	public int updateDeniedStatus(Long userId,String statusMessage);
	public User approvalDetail(Long userId);
}
