package com.signin.request.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.signin.request.custome.annotation.RepartPasswordEqual;
import com.signin.request.custome.annotation.ValidGmail;
import com.signin.request.modal.Role;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@RepartPasswordEqual(passwordFieldFirst = "password", passwordFieldSecond = "confirmPassword")
public class AuthsignUpRequest {

	@ValidGmail
	@NotNull
	private String gmail;
	@Size(min = 6, max = 43)
	private String password;
	@Size(min = 6, max = 43)
	private String confirmPassword;
	@NotNull
	private Role role;

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public AuthsignUpRequest(@NotNull String gmail, @Size(min = 6, max = 43) String password,
			@Size(min = 6, max = 43) String confirmPassword, @NotNull Role role) {
		super();
		this.gmail = gmail;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
	}

	public AuthsignUpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
