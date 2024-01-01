package com.signin.request.payload.request;

import com.signin.request.custome.annotation.ValidGmail;

public class OtherSignInRequestPayload {

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

	public OtherSignInRequestPayload(String gmail, String password) {
		super();
		this.gmail = gmail;
		this.password = password;
	}

	public OtherSignInRequestPayload() {
		super();
		// TODO Auto-generated constructor stub
	}

}
