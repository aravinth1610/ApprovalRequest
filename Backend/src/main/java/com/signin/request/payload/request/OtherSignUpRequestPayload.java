package com.signin.request.payload.request;

import java.util.Set;

import com.signin.request.custome.annotation.ValidGmail;

import jakarta.validation.constraints.Size;

public class OtherSignUpRequestPayload {

	@ValidGmail
	private String gmail;
	@Size(min = 3, max = 43)
	private String name;
	private String password;
	@Size(min = 7, max = 16)
	private String phoneNo;
	@Size(min = 3, max = 64)
	private String companyName;
	private OtherStatusRequestPayload status;

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public OtherStatusRequestPayload getStatus() {
		return status;
	}

	public void setStatus(OtherStatusRequestPayload status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public OtherSignUpRequestPayload(String gmail, @Size(min = 6, max = 43) String name, String password,
			@Size(min = 7, max = 16) String phoneNo, @Size(min = 3, max = 64) String companyName,
			OtherStatusRequestPayload status) {
		super();
		this.gmail = gmail;
		this.name = name;
		this.password = password;
		this.phoneNo = phoneNo;
		this.companyName = companyName;
		this.status = status;
	}

	public OtherSignUpRequestPayload() {
		super();
		// TODO Auto-generated constructor stub
	}

}
