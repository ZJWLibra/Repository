package com.jw.bean;

import java.io.Serializable;

import com.jw.common.AutoModel;

/**
 * 车型
 * 
 * @author Zeng
 *
 */
public class CarModel extends AutoModel implements Serializable {
	private static final long serialVersionUID = -8023108988395035417L;

	private String modelId;
	private String modelName;
	private String modelDes;
	private String brandId;
	private String modelStatus;

	private CarBrand carBrand;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelDes() {
		return modelDes;
	}

	public void setModelDes(String modelDes) {
		this.modelDes = modelDes;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(String modelStatus) {
		this.modelStatus = modelStatus;
	}

	public CarBrand getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(CarBrand carBrand) {
		this.carBrand = carBrand;
	}

}
