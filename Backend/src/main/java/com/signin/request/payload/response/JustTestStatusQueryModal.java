package com.signin.request.payload.response;

public class JustTestStatusQueryModal {

	private String status;
	private String statusMessage;
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
	public JustTestStatusQueryModal(String status, String statusMessage) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
	}
	public JustTestStatusQueryModal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
