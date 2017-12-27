package com.jw.model;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable {
	private static final long serialVersionUID = 2690132254734675689L;

	private String userRoleId;
	private String userId;
	private String roleId;
	private Date urCreatedTime;

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getUrCreatedTime() {
		return urCreatedTime;
	}

	public void setUrCreatedTime(Date urCreatedTime) {
		this.urCreatedTime = urCreatedTime;
	}

}
