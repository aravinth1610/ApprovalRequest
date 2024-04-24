package com.signin.request.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signin.request.payload.response.DashboardResponseModal;
import com.signin.request.repository.UserRepository;
import com.signin.request.services.DashboardServices;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DashboardServicesImpl implements DashboardServices {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<DashboardResponseModal> getAllUserDetails() {

		for (DashboardResponseModal d : userRepository.getAllUserDetails()) {
			System.out.println(d.getStatus());
		}

		System.out.println();
		return userRepository.getAllUserDetails();
	}
}
