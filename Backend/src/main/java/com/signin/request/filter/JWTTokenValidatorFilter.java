package com.signin.request.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.signin.request.constant.AppConstant;
import com.signin.request.entity.AuthUser;
import com.signin.request.repository.AuthUserRepository;
import com.signin.request.securityConfig.UserDetailServiceImpl;
import com.signin.request.securityConfig.UserPrincipal;
import com.signin.request.token.services.JWTTokenValidation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

	@Autowired
	private JWTTokenValidation jwtTokenValidation;
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("auth---");
		if (request.getMethod().equalsIgnoreCase(AppConstant.OPTIONS_HTTP_METHOD)) {
			response.setStatus(HttpStatus.OK.value());
		} else {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			System.out.println(authorizationHeader);
			if (authorizationHeader == null) {
				filterChain.doFilter(request, response);
				return;
			}
			String username = jwtTokenValidation.getClaimNameToken(authorizationHeader);
			System.out.println(username + "getuser");
			if (jwtTokenValidation.isTokenValid(username, authorizationHeader)
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println("fin");
			} else {
				System.out.println("e");
				SecurityContextHolder.clearContext();
			}

		}
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		System.out.println(AppConstant.SHOULDNOTFILTERVALIDATOR.contains(request.getServletPath())+"-"+request.getServletPath());
		return AppConstant.SHOULDNOTFILTERVALIDATOR.contains(request.getServletPath());
	}

}
