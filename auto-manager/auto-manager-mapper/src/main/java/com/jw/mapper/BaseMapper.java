package com.jw.mapper;

import java.util.List;

public interface BaseMapper<T> {
	
	void insert(T t) throws Exception;
	
	void delete(T t) throws Exception;
	
	void update(T t) throws Exception;
	
	List<T> list(T t) throws Exception;
	
	T get(T t) throws Exception;
	
	Long count(T t) throws Exception;
}
