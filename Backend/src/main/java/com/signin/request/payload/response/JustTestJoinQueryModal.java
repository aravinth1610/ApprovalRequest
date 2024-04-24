package com.signin.request.payload.response;

import java.sql.Date;

public class JustTestJoinQueryModal {

	private String gmail;
	private String status;
	private String statusMessage;
	private String authuser;

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getAuthuser() {
		return authuser;
	}

	public void setAuthuser(String authuser) {
		this.authuser = authuser;
	}

	public JustTestJoinQueryModal(String gmail, String status, String statusMessage, String authuser) {
		super();
		this.gmail = gmail;
		this.status = status;
		this.statusMessage = statusMessage;
		this.authuser = authuser;
	}

	public JustTestJoinQueryModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
