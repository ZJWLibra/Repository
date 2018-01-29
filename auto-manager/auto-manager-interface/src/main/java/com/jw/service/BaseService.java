package com.jw.service;

import com.jw.common.JqgridResult;

public interface BaseService<T> {
	
	void insert(T t) throws Exception;
	
	void delete(String[] ids) throws Exception;
	
	void update(T t) throws Exception;
	
	JqgridResult<T> list(T t) throws Exception;
	
	T get(T t) throws Exception;
	
}
