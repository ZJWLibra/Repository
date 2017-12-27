package com.jw.model.ext;

import java.util.List;

import com.jw.model.Permission;
import com.jw.model.User;

public class UserExt extends User {
	private static final long serialVersionUID = 1089335644405050560L;

	// 用户权限
	private List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
