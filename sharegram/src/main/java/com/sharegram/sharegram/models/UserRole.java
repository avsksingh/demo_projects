package com.sharegram.sharegram.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 5234567L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false) 
	private long userRoleId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private ApUser apUser;
	
	@ManyToOne(fetch= FetchType.EAGER)
	private Role role;
	
	public UserRole() {}

	public UserRole(ApUser appUser, Role role) {
		super();
		//this.userRoleId = userRoleId;
		this.apUser = apUser;
		this.role = role;
	}

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public ApUser getAppUser() {
		return apUser;
	}

	public void setAppUser(ApUser appUser) {
		this.apUser = appUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
}
