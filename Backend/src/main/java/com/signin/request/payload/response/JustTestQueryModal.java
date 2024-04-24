package com.signin.request.payload.response;

public class JustTestQueryModal {

	private String gmail;
	private JustTestStatusQueryModal justStatus;
	private String authuser;

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public JustTestStatusQueryModal getJustStatus() {
		return justStatus;
	}

	public void setJustStatus(JustTestStatusQueryModal justStatus) {
		this.justStatus = justStatus;
	}

	public String getAuthuser() {
		return authuser;
	}

	public void setAuthuser(String authuser) {
		this.authuser = authuser;
	}

	public JustTestQueryModal(String gmail, JustTestStatusQueryModal justStatus, String authuser) {
		super();
		this.gmail = gmail;
		this.justStatus = justStatus;
		this.authuser = authuser;
	}

	public JustTestQueryModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
