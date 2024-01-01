package com.signin.request.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.signin.request.entity.AuthUser;
import com.signin.request.entity.User;
import com.signin.request.repository.AuthUserRepository;
import com.signin.request.repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private AuthUserRepository authUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AuthUser user = authUserRepository.findByGmail(username);
		try {
			if (user != null) {
				return new UserPrincipal(user);
			} else {
				throw new UsernameNotFoundException("Invalid Username and Password");
			}
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("Invalid Username and Password");
		}
	}

}
