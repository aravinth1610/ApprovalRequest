package com.signin.request.services;

import java.util.List;
import java.util.Optional;

import com.signin.request.payload.response.DashboardResponseModal;

public interface DashboardServices {
	
	List<DashboardResponseModal> getAllUserDetails();
	
}
