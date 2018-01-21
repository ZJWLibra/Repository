package com.jw.service;

import java.util.List;

import com.jw.model.CarBrand;
import com.jw.model.CarModel;

public interface CarModelService extends BaseService<CarModel> {

	List<CarBrand> listCarBrand();
	
}
