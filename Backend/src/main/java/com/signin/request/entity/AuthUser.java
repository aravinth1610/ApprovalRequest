package com.signin.request.entity;

import java.sql.Date;
import java.util.Objects;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.signin.request.modal.Role;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authuser")
public class AuthUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String gmail;
	@JsonIgnore
	private String password;
	@Enumerated(EnumType.STRING)
	private Role authority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getAuthority() {
		return authority;
	}

	public void setAuthority(Role authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gmail, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthUser other = (AuthUser) obj;
		return Objects.equals(gmail, other.gmail) && Objects.equals(id, other.id);
	}

	public AuthUser(Long id, String gmail, String password, Role authority) {
		super();
		this.id = id;
		this.gmail = gmail;
		this.password = password;
		this.authority = authority;
	}

	public AuthUser() {
		super();
		// TODO Auto-generated constructor stub
	}

}
