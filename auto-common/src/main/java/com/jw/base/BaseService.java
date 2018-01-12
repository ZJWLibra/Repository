package com.jw.base;

import com.jw.model.common.AutoResult;
import com.jw.model.common.JqgridResult;

public interface BaseService<T> {
	
	AutoResult insert(T t);
	
	AutoResult delete(String[] ids);
	
	AutoResult update(T t);
	
	JqgridResult<T> list(T t);
	
	T get(T t);
	
}
