package com.jw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.model.CarBrand;
import com.jw.model.CarModel;
import com.jw.service.CarModelService;

/**
 * 车型
 * 
 * @author Zeng
 *
 */
@Controller
@RequestMapping("/carModel")
public class CarModelController {
	
	@Resource
	private CarModelService carModelService;
	
	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		List<CarBrand> carBrandList = carModelService.listCarBrand();
		model.addAttribute("carBrandList", carBrandList);
		return "carModel/carModelIndex";
	}
	
	@RequestMapping("/list")
	public @ResponseBody JqgridResult<CarModel> list(CarModel carModel) {
		JqgridResult<CarModel> list = carModelService.list(carModel);
		return list;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(CarModel carModel) {
		AutoResult result = carModelService.insert(carModel);
		return result;
	}
	
	@RequestMapping("/getById")
	public @ResponseBody CarModel getById(String modelId) {
		CarModel carModel = new CarModel();
		carModel.setModelId(modelId);
		carModel = carModelService.get(carModel);
		return carModel;
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(CarModel carModel) {
		AutoResult result = carModelService.update(carModel);
		return result;
	}
	
}
