package com.jw.model;

import java.io.Serializable;
import java.util.Date;

public class RolePermission implements Serializable {
	private static final long serialVersionUID = 6910540202847567289L;

	private String rpId;
	private String roleId;
	private String permissionId;
	private Date rpCreatedTime;

	public String getRpId() {
		return rpId;
	}

	public void setRpId(String rpId) {
		this.rpId = rpId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public Date getRpCreatedTime() {
		return rpCreatedTime;
	}

	public void setRpCreatedTime(Date rpCreatedTime) {
		this.rpCreatedTime = rpCreatedTime;
	}

}
