package com.jw.bean;

import java.io.Serializable;

import com.jw.common.AutoModel;

/**
 * 车牌
 * 
 * @author Zeng
 *
 */
public class CarPlate extends AutoModel implements Serializable {
	private static final long serialVersionUID = 93951614658277883L;

	private String plateId;
	private String plateName;
	private String plateDes;
	private String plateStatus;

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

	public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}

	public String getPlateDes() {
		return plateDes;
	}

	public void setPlateDes(String plateDes) {
		this.plateDes = plateDes;
	}

	public String getPlateStatus() {
		return plateStatus;
	}

	public void setPlateStatus(String plateStatus) {
		this.plateStatus = plateStatus;
	}

}
