package com.signin.request.entity;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.Index;
import org.springframework.stereotype.Indexed;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientotheruser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long userId;
	private String gmail;
	private String name;
	@JsonIgnore
	private String password;
	@Column(name = "phoneno")
	private String phoneNo;
	@Column(name = "Companyname")
	private String companyName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "createdon")
	private Date createdOn;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updatedon")
	private Date updateOn;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Status status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gmail, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(gmail, other.gmail) && Objects.equals(userId, other.userId);
	}

	public User(Long userId, String gmail, String name, String password, String phoneNo, String companyName,
			Date createdOn, Date updateOn, Status status) {
		super();
		this.userId = userId;
		this.gmail = gmail;
		this.name = name;
		this.password = password;
		this.phoneNo = phoneNo;
		this.companyName = companyName;
		this.createdOn = createdOn;
		this.updateOn = updateOn;
		this.status = status;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String gmail, String name, String password, String phoneNo, String companyName, Date createdOn,
			Date updateOn, Status status) {
		super();
		this.gmail = gmail;
		this.name = name;
		this.password = password;
		this.phoneNo = phoneNo;
		this.companyName = companyName;
		this.createdOn = createdOn;
		this.updateOn = updateOn;
		this.status = status;
	}
	

}
