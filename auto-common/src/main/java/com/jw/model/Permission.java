package com.jw.model;

import java.io.Serializable;

public class Permission implements Serializable {
	private static final long serialVersionUID = 1527238623468175543L;

	private String permissionId;
	private String permissionName;
	private String permissionType;
	private String permissionPercode;
	private String permissionParentid;
	private String permissionSequence;
	private String permissionStatus;

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getPermissionPercode() {
		return permissionPercode;
	}

	public void setPermissionPercode(String permissionPercode) {
		this.permissionPercode = permissionPercode;
	}

	public String getPermissionParentid() {
		return permissionParentid;
	}

	public void setPermissionParentid(String permissionParentid) {
		this.permissionParentid = permissionParentid;
	}

	public String getPermissionSequence() {
		return permissionSequence;
	}

	public void setPermissionSequence(String permissionSequence) {
		this.permissionSequence = permissionSequence;
	}

	public String getPermissionStatus() {
		return permissionStatus;
	}

	public void setPermissionStatus(String permissionStatus) {
		this.permissionStatus = permissionStatus;
	}

}
