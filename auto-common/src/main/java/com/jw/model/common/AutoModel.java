package com.jw.model.common;

import java.io.Serializable;

public class AutoModel implements Serializable {
	private static final long serialVersionUID = 4925999999652168959L;

	// 要查询的页数
	private Long page;
	// 每页显示条数
	private Long rows;
	// 开始查询的下标
	private Long beginIndex;
	// 排序字段
	private String sidx;
	// 排序方式
	private String sord;

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}

	public Long getBeginIndex() {
		if (page != null && !page.equals("") && rows != null && !rows.equals("")) {
			return (page - 1) * rows;
		}
		
		return beginIndex;
	}

	public void setBeginIndex(Long beginIndex) {
		this.beginIndex = beginIndex;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

}
