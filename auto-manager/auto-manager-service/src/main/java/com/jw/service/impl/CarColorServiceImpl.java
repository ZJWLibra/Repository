package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.CarColor;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarColorMapper;
import com.jw.service.CarColorService;

@Service
@Transactional
public class CarColorServiceImpl implements CarColorService {
	@Resource
	private CarColorMapper carColorMapper;

	@Override
	public void insert(CarColor t) throws Exception {
		carColorMapper.insert(t);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		
	}

	@Override
	public void update(CarColor t) throws Exception {
		carColorMapper.update(t);
	}

	@Override
	public JqgridResult<CarColor> list(CarColor t) throws Exception {
		List<CarColor> list = carColorMapper.list(t);
		Long count = carColorMapper.count(t);
		JqgridResult<CarColor> result = new JqgridResult<CarColor>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarColor get(CarColor t) throws Exception {
		return carColorMapper.get(t);
	}

}
