package com.jw.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.bean.CarPlate;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.service.CarPlateService;

@Controller
@RequestMapping("/carPlate")
public class CarPlateController {
	@Resource
	private CarPlateService carPlateService;
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "carPlate/carPlateIndex";
	}
	
	@RequestMapping("/list")
	public @ResponseBody JqgridResult<CarPlate> list(CarPlate carPlate) {
		JqgridResult<CarPlate> result;
		try {
			result = carPlateService.list(carPlate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(CarPlate carPlate) {
		try {
			carPlateService.insert(carPlate);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}
	
	@RequestMapping("/getById")
	public @ResponseBody CarPlate getById(String plateId) {
		CarPlate carPlate = new CarPlate();
		carPlate.setPlateId(plateId);
		try {
			carPlate = carPlateService.get(carPlate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return carPlate;
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(CarPlate carPlate) {
		try {
			carPlateService.update(carPlate);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}

}
