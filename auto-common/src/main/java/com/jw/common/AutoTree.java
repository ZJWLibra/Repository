package com.jw.common;

import java.io.Serializable;

public class AutoTree implements Serializable {
	private static final long serialVersionUID = -6823607602201834367L;
	
	private String id;
	// 父节点id
	private String pId;
	// 显示名称
	private String name;
	// 是否默认被选中
	private Boolean checked;
	// 是否默认打开
	private Boolean open;

	public AutoTree() {
	}

	public AutoTree(String id, String pId, String name, Boolean checked, Boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

}
