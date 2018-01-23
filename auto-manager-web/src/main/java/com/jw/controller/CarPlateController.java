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
		JqgridResult<CarPlate> result = carPlateService.list(carPlate);
		return result;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(CarPlate carPlate) {
		AutoResult result = carPlateService.insert(carPlate);
		return result;
	}
	
	@RequestMapping("/getById")
	public @ResponseBody CarPlate getById(String plateId) {
		CarPlate carPlate = new CarPlate();
		carPlate.setPlateId(plateId);
		carPlate = carPlateService.get(carPlate);
		return carPlate;
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(CarPlate carPlate) {
		AutoResult result = carPlateService.update(carPlate);
		return result;
	}

}
