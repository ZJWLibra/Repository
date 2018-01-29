package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.CarBrand;
import com.jw.bean.CarModel;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarBrandMapper;
import com.jw.mapper.CarModelMapper;
import com.jw.service.CarModelService;

@Service
@Transactional
public class CarModelServiceImpl implements CarModelService {
	@Resource
	private CarModelMapper carModelMapper;
	@Resource
	private CarBrandMapper carBrandMapper;

	@Override
	public void insert(CarModel t) throws Exception {
		carModelMapper.insert(t);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		
	}

	@Override
	public void update(CarModel t) throws Exception {
		carModelMapper.update(t);
	}

	@Override
	public JqgridResult<CarModel> list(CarModel t) throws Exception {
		JqgridResult<CarModel> result = new JqgridResult<CarModel>();
		Long count = carModelMapper.count(t);
		List<CarModel> list = carModelMapper.list(t);
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarModel get(CarModel t) throws Exception {
		return carModelMapper.get(t);
	}

	/**
	 * 查询所有品牌
	 */
	@Override
	public List<CarBrand> listCarBrand() throws Exception {
		return carBrandMapper.list(new CarBrand());
	}

}
