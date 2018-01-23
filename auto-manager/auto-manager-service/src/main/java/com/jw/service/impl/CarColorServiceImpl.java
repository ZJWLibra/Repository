package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.CarColor;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.mapper.CarColorMapper;
import com.jw.service.CarColorService;

@Service
@Transactional
public class CarColorServiceImpl implements CarColorService {
	
	@Resource
	private CarColorMapper carColorMapper;

	@Override
	public AutoResult insert(CarColor t) {
		try {
			carColorMapper.insert(t);
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
	public AutoResult update(CarColor t) {
		try {
			carColorMapper.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}

	@Override
	public JqgridResult<CarColor> list(CarColor t) {
		List<CarColor> list;
		Long count;
		try {
			count = carColorMapper.count(t);
			list = carColorMapper.list(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		JqgridResult<CarColor> result = new JqgridResult<CarColor>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public CarColor get(CarColor t) {
		try {
			return carColorMapper.get(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
