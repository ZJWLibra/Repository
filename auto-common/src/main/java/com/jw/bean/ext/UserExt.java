package com.jw.bean.ext;

import java.util.List;

import com.jw.bean.Permission;
import com.jw.bean.User;

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
