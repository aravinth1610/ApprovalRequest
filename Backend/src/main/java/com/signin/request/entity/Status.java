package com.signin.request.entity;

import java.util.Objects;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientstatus")
@DynamicInsert
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statusid")
	private Long statusId;
	
	@Column(name="status" , columnDefinition = "varchar(225) default 'Pending'")
	private String status;
	@Column(name="statusmessage" , columnDefinition = "varchar(225) default 'Need to be Approval'")
	private String statusMessage;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusId, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return Objects.equals(statusId, other.statusId) && Objects.equals(user, other.user);
	}

	public Status(Long statusId, String status, String statusMessage, User user) {
		super();
		this.statusId = statusId;
		this.status = status;
		this.statusMessage = statusMessage;
		this.user = user;
	}

	public Status(String status, String statusMessage,User user) {
		super();
		this.status = status;
		this.statusMessage = statusMessage;
	}

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

}
