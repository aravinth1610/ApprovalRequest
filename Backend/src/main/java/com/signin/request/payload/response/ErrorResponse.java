package com.signin.request.payload.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	private Integer statusCode;
	private String messgae;
	private HttpStatus status;
	private String reason;
	private String validationErrors;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Asia/Dhaka")
	private Date timestamp;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(String validationErrors) {
		this.validationErrors = validationErrors;
	}

	public ErrorResponse(Integer statusCode, String messgae, HttpStatus status, String reason, String validationErrors,
			Date timestamp) {
		super();
		this.statusCode = statusCode;
		this.messgae = messgae;
		this.status = status;
		this.reason = reason;
		this.validationErrors = validationErrors;
		this.timestamp = timestamp;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
