package com.signin.request.securityConfig;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.signin.request.constant.AppConstant;
import com.signin.request.filter.CorsConfigurationFilter;
import com.signin.request.filter.JWTTokenValidatorFilter;
import com.signin.request.filter.RoleBaseAPIFilter;
import com.signin.request.gloable.exception.CustomAccessDeniedHandler;
import com.signin.request.gloable.exception.CustomAuthenticationEntryPoint;

@Configuration
public class WebSecurity {

	@Autowired
	private UserDetailServiceImpl userDetailServices;
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private CustomAuthenticationEntryPoint customeAuthEntryPoint;
	@Autowired
	private CorsConfigurationFilter corsConfigurationFilter;
	@Autowired
	private JWTTokenValidatorFilter jwtTokenValidatorFilter;
	@Autowired
	private RoleBaseAPIFilter roleBase;
	

	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailServices);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	/*
	 * If any API is common for the Next Particualr Role API you need to put last or
	 * next to all match API Eg: 1)signinrequest/authorization/** -> I had all the
	 * API in this APIs. 2)signinrequest/authorization/dashboard -> In this API
	 * their is particular API but I had common API in top,so need to put next or
	 * last APIs.
	 */

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.cors(corsCustomize -> corsCustomize.configurationSource(corsConfigurationFilter))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
				.authorizeHttpRequests(auth -> {
					this.roleBase.getPermissions().forEach(permission -> {
						 try {
						auth.requestMatchers(HttpMethod.valueOf(permission.getMethod()), permission.getEndpoints())
								.hasAnyAuthority(permission.getRoles().split(","));
						
						 } catch (Exception e) {
		                        throw new RuntimeException("Failed to configure authorization", e);
		                    }
					});
					 auth.requestMatchers(AppConstant.PUBLIC_URLS).permitAll().anyRequest().authenticated();
// --------------------------------------------------------------------------------------------------------------------				
//						        auth.requestMatchers(HttpMethod.GET, "/signinrequest/authorization/dashboard")
//								.hasAnyAuthority("USER", "ADMIN").requestMatchers("/signinrequest/authorization/**")
//								.hasAuthority("ADMIN").requestMatchers(AppConstant.PUBLIC_URLS).permitAll().anyRequest()
//								.authenticated();      
// ------------------------------------------------------------------------------------------------------------------------				
				})
				.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler)
						.authenticationEntryPoint(customeAuthEntryPoint))
				.formLogin(formLogin -> formLogin.disable()).httpBasic(httpBasic -> httpBasic.disable());
		return http.build();
	}

}
