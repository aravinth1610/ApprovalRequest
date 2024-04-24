package com.signin.request.payload.response;

import com.signin.request.modal.Role;

public class JustTestNonRelationQuery {

	private String gmail;
	private String status;
	private String phoneNo;
	private String companyName;
	private Role authority;

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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Role getAuthority() {
		return authority;
	}

	public void setAuthority(Role authority) {
		this.authority = authority;
	}

	public JustTestNonRelationQuery(String gmail, String status, String phoneNo, String companyName, Role authority) {
		super();
		this.gmail = gmail;
		this.status = status;
		this.phoneNo = phoneNo;
		this.companyName = companyName;
		this.authority = authority;
	}

	public JustTestNonRelationQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

}
