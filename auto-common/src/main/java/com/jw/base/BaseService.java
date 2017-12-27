package com.jw.base;

import java.util.List;

import com.jw.model.common.AutoResult;

public interface BaseService<T> {
	
	AutoResult insert(T t);
	
	AutoResult delete(String[] ids);
	
	AutoResult update(T t);
	
	List<T> list(T t);
	
	T get(T t);
	
	Integer count(T t);
}
