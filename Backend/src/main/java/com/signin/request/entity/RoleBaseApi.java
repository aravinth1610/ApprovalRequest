package com.signin.request.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "apibaseaut")
public class RoleBaseApi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resource_id")
	private Long resourceId;
	@Column(name = "method_role")
	private String method;
	private String endpoints;
	private String roles;
	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(String endpoints) {
		this.endpoints = endpoints;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public RoleBaseApi(Long resourceId, String method, String endpoints,String roles) {
		super();
		this.resourceId = resourceId;
		this.method = method;
		this.endpoints = endpoints;
		this.roles = roles;
	}

	public RoleBaseApi() {
		super();
		// TODO Auto-generated constructor stub
	}

}
