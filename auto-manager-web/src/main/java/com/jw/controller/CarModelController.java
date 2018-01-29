package com.jw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.bean.CarBrand;
import com.jw.bean.CarModel;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
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
		List<CarBrand> carBrandList;
		try {
			carBrandList = carModelService.listCarBrand();
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
		model.addAttribute("carBrandList", carBrandList);
		return "carModel/carModelIndex";
	}
	
	@RequestMapping("/list")
	public @ResponseBody JqgridResult<CarModel> list(CarModel carModel) {
		JqgridResult<CarModel> list;
		try {
			list = carModelService.list(carModel);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(CarModel carModel) {
		try {
			carModelService.insert(carModel);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}
	
	@RequestMapping("/getById")
	public @ResponseBody CarModel getById(String modelId) {
		CarModel carModel = new CarModel();
		carModel.setModelId(modelId);
		try {
			carModel = carModelService.get(carModel);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return carModel;
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(CarModel carModel) {
		try {
			carModelService.update(carModel);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}
	
}
