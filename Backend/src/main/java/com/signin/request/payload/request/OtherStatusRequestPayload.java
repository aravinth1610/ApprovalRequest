package com.signin.request.payload.request;

import jakarta.validation.constraints.Size;

public class OtherStatusRequestPayload {

	@Size(min = 7, max = 54)
	private String statusMessage;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public OtherStatusRequestPayload(@Size(min = 7, max = 54) String statusMessage) {
		super();
		this.statusMessage = statusMessage;
	}

	public OtherStatusRequestPayload() {
		super();
		// TODO Auto-generated constructor stub
	}

}
