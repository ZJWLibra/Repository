package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.CarBrand;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarBrandMapper;
import com.jw.service.CarBrandService;

@Service
@Transactional
public class CarBrandServiceImpl implements CarBrandService {
	@Resource
	private CarBrandMapper carBrandMapper;

	@Override
	public void insert(CarBrand t) throws Exception {
		carBrandMapper.insert(t);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		
	}

	@Override
	public void update(CarBrand t) throws Exception {
		carBrandMapper.update(t);
	}

	@Override
	public JqgridResult<CarBrand> list(CarBrand t) throws Exception {
		Long count = carBrandMapper.count(t);
		List<CarBrand> list = carBrandMapper.list(t);
		JqgridResult<CarBrand> result = new JqgridResult<>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarBrand get(CarBrand t) throws Exception {
		return carBrandMapper.get(t);
	}

}
