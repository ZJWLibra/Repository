package com.jw.service;

import java.util.List;

import com.jw.bean.CarBrand;
import com.jw.bean.CarModel;

public interface CarModelService extends BaseService<CarModel> {

	List<CarBrand> listCarBrand() throws Exception;
	
}
