package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.CarPlate;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarPlateMapper;
import com.jw.service.CarPlateService;

@Service
@Transactional
public class CarPlateServiceImpl implements CarPlateService {
	@Resource
	private CarPlateMapper carPlateMapper;

	@Override
	public void insert(CarPlate t) throws Exception {
		carPlateMapper.insert(t);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		
	}

	@Override
	public void update(CarPlate t) throws Exception {
		carPlateMapper.update(t);
	}

	@Override
	public JqgridResult<CarPlate> list(CarPlate t) throws Exception {
		Long count = carPlateMapper.count(t);
		List<CarPlate> list = carPlateMapper.list(t);
		JqgridResult<CarPlate> result = new JqgridResult<>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarPlate get(CarPlate t) throws Exception {
		return carPlateMapper.get(t);
	}

}
