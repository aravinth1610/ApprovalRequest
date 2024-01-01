package com.signin.request.filter;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.signin.request.constant.AppConstant;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class CorsConfigurationFilter implements CorsConfigurationSource {

	@Override
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList(AppConstant.CORS_ALLOW_Methods));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList(AppConstant.CORS_ALLOW_HEADERS));
		config.setExposedHeaders(Arrays.asList(AppConstant.CORS_ALLOW_EXPOSEDHEADERS));
		config.setMaxAge(3600L);
		return config;

	}

}
