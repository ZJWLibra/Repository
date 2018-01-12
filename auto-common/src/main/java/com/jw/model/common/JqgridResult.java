package com.jw.model.common;

import java.io.Serializable;
import java.util.List;

public class JqgridResult<T> implements Serializable {
	private static final long serialVersionUID = 6044033221135049472L;

	// 查询的页数
	private Long page;
	// 总记录数
	private Long records;
	// 总页数
	private Long total;
	// 结果集
	private List<T> rows;

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public Long getTotal() {
		return total;
	}
	
	public Long getTotal(Long pageSize) {
		long num = records % pageSize;
        return num == 0 ? records / pageSize : records / pageSize + 1;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
