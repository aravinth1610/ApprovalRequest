package com.signin.request.payload.response;

import java.sql.Date;

public class DashboardResponseModal {

	private String gmail;
	private String name;
	private String phoneNo;
	private String companyName;
	private Date createdOn;
	private Date updateOn;
	private String status;

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
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DashboardResponseModal(String gmail, String name, String phoneNo, String companyName, Date createdOn,
			Date updateOn, String status) {
		super();
		this.gmail = gmail;
		this.name = name;
		this.phoneNo = phoneNo;
		this.companyName = companyName;
		this.createdOn = createdOn;
		this.updateOn = updateOn;
		this.status = status;
			}

	public DashboardResponseModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
