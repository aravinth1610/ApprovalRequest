package com.signin.request.services;

import java.util.List;
import java.util.Optional;

import com.signin.request.entity.Status;
import com.signin.request.entity.User;

public interface PendingServices {

	User pendingDetail(Long statusId);
	
	List<User> getPendingDetails();
	
    int updateApprovalStatus(Long userId);
    
    public int updateDeniedStatus(Long userId,String statusMessage);
	
}
