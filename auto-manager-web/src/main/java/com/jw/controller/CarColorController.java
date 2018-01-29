package com.jw.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.bean.CarColor;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.service.CarColorService;

@Controller
@RequestMapping("/carColor")
public class CarColorController {
	
	@Resource
	private CarColorService carColorService;
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "carColor/carColorIndex";
	}
	
	@RequestMapping("/list")
	public @ResponseBody JqgridResult<CarColor> list(CarColor carColor) {
		JqgridResult<CarColor> list;
		try {
			list = carColorService.list(carColor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(CarColor carColor) {
		try {
			carColorService.insert(carColor);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}
	
	@RequestMapping("/getById")
	public @ResponseBody CarColor getById(String colorId) {
		CarColor carColor = new CarColor();
		carColor.setColorId(colorId);
		try {
			carColor = carColorService.get(carColor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return carColor;
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(CarColor carColor) {
		try {
			carColorService.update(carColor);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}
	
}
