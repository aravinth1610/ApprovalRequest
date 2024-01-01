package com.signin.request.servicesImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.signin.request.entity.AuthUser;
import com.signin.request.mapper.CreateNewUserMapper;
import com.signin.request.payload.request.AuthsignInRequest;
import com.signin.request.payload.request.AuthsignUpRequest;
import com.signin.request.repository.AuthUserRepository;
import com.signin.request.services.AuthUserServices;
import com.signin.request.token.services.JWTCookiesServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthUserServicesImpl implements AuthUserServices {

	@Value("${signInRequest.app.jwtCookieName}")
	private String JWTCookiesName;

	
	@Autowired
	private CreateNewUserMapper mapStructMappterUpdate;
	@Autowired
	private AuthUserRepository authUserRepository;
	@Autowired
	private JWTCookiesServices jwtCookiesServices;

	@Override
	public boolean createAuthNewUser(AuthsignUpRequest newUser) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		AuthUser user = new AuthUser();
		user.setGmail(newUser.getGmail());
		user.setPassword(encoder.encode(newUser.getPassword()));
		user.setAuthority(newUser.getRole());

		// mapStructMappterUpdate.createNewUserFromRequestPayload(user, newUser);
		authUserRepository.save(user);
		return true;
	}

	@Override
	public boolean alreadyEmailIsExists(String gmail) {
		return authUserRepository.existsByGmail(gmail);
	}

	@Override
	public String generateRefressToken(HttpServletRequest request) {
		String refreshToken = jwtCookiesServices.getJwtFromCookies(request,JWTCookiesName);
		System.out.println(refreshToken+"--jwt");
		if (refreshToken != null) {
			boolean isRefreshTokenValid = jwtCookiesServices.validateRefreshToken(refreshToken);
			return ((isRefreshTokenValid) ? jwtCookiesServices.GenerateForHeaderToken() : "UNAUTHORIZED");
		} else {
			return "UNAUTHORIZED";
		}
	}

}
