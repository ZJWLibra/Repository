package com.jw.model;

import java.io.Serializable;

import com.jw.common.AutoModel;

/**
 * 品牌
 * 
 * @author Zeng
 *
 */
public class CarBrand extends AutoModel implements Serializable {
	private static final long serialVersionUID = 3750677157004332143L;

	private String brandId;
	private String brandName;
	private String brandDes;
	private String brandLogo;
	private String brandStatus;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDes() {
		return brandDes;
	}

	public void setBrandDes(String brandDes) {
		this.brandDes = brandDes;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public String getBrandStatus() {
		return brandStatus;
	}

	public void setBrandStatus(String brandStatus) {
		this.brandStatus = brandStatus;
	}

}
