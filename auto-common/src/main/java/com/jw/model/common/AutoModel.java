package com.jw.model.common;

import java.io.Serializable;

public class AutoModel implements Serializable {
	private static final long serialVersionUID = 4925999999652168959L;

	// 要查询的页数
	private Integer page;
	// 每页显示条数
	private Integer rows;
	// 总记录数
	private Integer records;
	// 总页数
	private Integer total;
	// 开始条数
	private Integer startData;
	// 排序字段
	private String sidx;
	// 排序方式
	private String sord;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getStartData() {
		return startData;
	}

	public void setStartData(Integer startData) {
		this.startData = startData;
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
