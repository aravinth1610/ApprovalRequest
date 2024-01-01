package com.signin.request.securityConfig;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.signin.request.entity.AuthUser;
import com.signin.request.entity.User;
import com.signin.request.modal.Role;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String gmail;
	private String role;

	@JsonIgnore
	private String password;

	AuthUser user = new AuthUser();

	public UserPrincipal() {
		super();
	}

	public UserPrincipal(AuthUser user) {
		this.user = user;
		this.userId = user.getId();
		this.gmail = user.getGmail();
		this.password = user.getPassword();
		this.role = user.getAuthority().name();
		System.out.println(this.role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.<GrantedAuthority>of(new SimpleGrantedAuthority(this.role));
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userId.toString();
	}

	public String getGmail() {
		return this.gmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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
		UserPrincipal other = (UserPrincipal) obj;
		return Objects.equals(gmail, other.gmail) && Objects.equals(userId, other.userId);
	}

}
