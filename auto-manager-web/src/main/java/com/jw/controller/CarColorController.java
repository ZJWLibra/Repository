package com.jw.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
		JqgridResult<CarColor> list = carColorService.list(carColor);
		return list;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(CarColor carColor) {
		AutoResult result = carColorService.insert(carColor);
		return result;
	}
	
	@RequestMapping("/getById")
	public @ResponseBody CarColor getById(String colorId) {
		CarColor carColor = new CarColor();
		carColor.setColorId(colorId);
		carColor = carColorService.get(carColor);
		return carColor;
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(CarColor carColor) {
		AutoResult result = carColorService.update(carColor);
		return result;
	}
	
}
