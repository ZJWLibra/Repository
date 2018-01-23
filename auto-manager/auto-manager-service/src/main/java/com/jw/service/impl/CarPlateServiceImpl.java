package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.CarPlate;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarPlateMapper;
import com.jw.service.CarPlateService;

@Service
@Transactional
public class CarPlateServiceImpl implements CarPlateService {
	
	@Resource
	private CarPlateMapper carPlateMapper;

	@Override
	public AutoResult insert(CarPlate t) {
		try {
			carPlateMapper.insert(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}

	@Override
	public AutoResult delete(String[] ids) {
		return null;
	}

	@Override
	public AutoResult update(CarPlate t) {
		try {
			carPlateMapper.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}

	@Override
	public JqgridResult<CarPlate> list(CarPlate t) {
		Long count;
		List<CarPlate> list;
		try {
			count = carPlateMapper.count(t);
			list = carPlateMapper.list(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		JqgridResult<CarPlate> result = new JqgridResult<>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarPlate get(CarPlate t) {
		try {
			return carPlateMapper.get(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
