package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarBrandMapper;
import com.jw.mapper.CarModelMapper;
import com.jw.model.CarBrand;
import com.jw.model.CarModel;
import com.jw.service.CarModelService;

@Service
@Transactional
public class CarModelServiceImpl implements CarModelService {
	
	@Resource
	private CarModelMapper carModelMapper;
	@Resource
	private CarBrandMapper carBrandMapper;

	@Override
	public AutoResult insert(CarModel t) {
		try {
			carModelMapper.insert(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}

	@Override
	public AutoResult delete(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutoResult update(CarModel t) {
		try {
			carModelMapper.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}

	@Override
	public JqgridResult<CarModel> list(CarModel t) {
		JqgridResult<CarModel> result = new JqgridResult<CarModel>();
		Long count;
		List<CarModel> list;
		try {
			count = carModelMapper.count(t);
			list = carModelMapper.list(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarModel get(CarModel t) {
		try {
			return carModelMapper.get(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询所有品牌
	 */
	@Override
	public List<CarBrand> listCarBrand() {
		try {
			return carBrandMapper.list(new CarBrand());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
