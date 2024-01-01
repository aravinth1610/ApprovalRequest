package com.signin.request.payload.request;

import com.signin.request.custome.annotation.ValidGmail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthsignInRequest {

	@ValidGmail
	private String gmail;
	private String password;

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

	public AuthsignInRequest(String gmail, String password) {
		super();
		this.gmail = gmail;
		this.password = password;
	}

	public AuthsignInRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
