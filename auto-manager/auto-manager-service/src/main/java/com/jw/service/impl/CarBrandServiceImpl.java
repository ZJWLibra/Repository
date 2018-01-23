package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.CarBrand;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarBrandMapper;
import com.jw.service.CarBrandService;

@Service
@Transactional
public class CarBrandServiceImpl implements CarBrandService {

	@Resource
	private CarBrandMapper carBrandMapper;

	@Override
	public AutoResult insert(CarBrand t) {
		try {
			carBrandMapper.insert(t);
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
	public AutoResult update(CarBrand t) {
		try {
			carBrandMapper.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}

	@Override
	public JqgridResult<CarBrand> list(CarBrand t) {
		Long count;
		List<CarBrand> list;
		try {
			count = carBrandMapper.count(t);
			list = carBrandMapper.list(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		JqgridResult<CarBrand> result = new JqgridResult<>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarBrand get(CarBrand t) {
		try {
			return carBrandMapper.get(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
