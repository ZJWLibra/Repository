package com.jw.service;

import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;

public interface BaseService<T> {
	
	AutoResult insert(T t);
	
	AutoResult delete(String[] ids);
	
	AutoResult update(T t);
	
	JqgridResult<T> list(T t);
	
	T get(T t);
	
}
