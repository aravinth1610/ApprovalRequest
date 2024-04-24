package com.signin.request.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import com.signin.request.entity.RoleBaseApi;
import com.signin.request.repository.RoleBaseApiRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class RoleBaseAPIFilter  {

	private final RoleBaseApiRepository roleBaseApiRepo;

	private RoleBaseAPIFilter(RoleBaseApiRepository roleBaseApiRepo) {
		this.roleBaseApiRepo = roleBaseApiRepo;
	}

	
	public List<RoleBaseApi> getPermissions() {
		System.out.println("-------------------------------");
		List<RoleBaseApi> d = roleBaseApiRepo.findAll();
		String[] st = d.get(0).getRoles().split(",");
		System.out.println(Arrays.asList(st));
		System.out.println(d.get(0).getEndpoints()+"-"+d.get(0).getMethod()+"-"+st);
	return d;
	}	
}
