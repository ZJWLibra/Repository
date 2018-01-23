package com.jw.bean;

import java.io.Serializable;

import com.jw.common.AutoModel;

public class CarColor extends AutoModel implements Serializable {
	private static final long serialVersionUID = 5942958479849761938L;

	private String colorId;
	private String colorName;
	private String colorRGB;
	private String colorHex;
	private String colorDes;
	private String colorStatus;

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorRGB() {
		return colorRGB;
	}

	public void setColorRGB(String colorRGB) {
		this.colorRGB = colorRGB;
	}

	public String getColorHex() {
		return colorHex;
	}

	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}

	public String getColorDes() {
		return colorDes;
	}

	public void setColorDes(String colorDes) {
		this.colorDes = colorDes;
	}

	public String getColorStatus() {
		return colorStatus;
	}

	public void setColorStatus(String colorStatus) {
		this.colorStatus = colorStatus;
	}

}
