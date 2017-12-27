package com.jw.model;

import java.io.Serializable;

import com.jw.model.common.AutoModel;

public class Role extends AutoModel implements Serializable {
	private static final long serialVersionUID = 2034470845886586328L;

	private String roleId;
	private String roleName;
	private String roleDes;
	private String roleStatus;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDes() {
		return roleDes;
	}

	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

}
