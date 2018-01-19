package com.jw.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jw.common.AutoModel;

/**
 * 公司
 * 
 * @author Zeng
 *
 */
public class Company extends AutoModel implements Serializable {
	private static final long serialVersionUID = -5564334427687802836L;

	private String companyId;
	// 公司名称
	private String companyName;
	// 公司地址
	private String companyAddress;
	// 公司联系方式
	private String companyTel;
	// 公司负责人电话
	private String companyPricipalPhone;
	// 公司负责人
	private String companyPricipal;
	// 公司图片
	private String companyImg;
	// 公司官网URL
	private String companyWebsite;
	// 公司logo图片
	private String companyLogo;
	// 公司成立时间
	private Date companyOpenDate;
	// 公司规模
	private String companySize;
	// 公司经度
	private String companyLongitude;
	// 公司纬度
	private String companyLatitude;
	// 公司描述
	private String companyDes;
	// 公司状态
	private String companyStatus;
	// 创建时间
	private Date createTime;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyPricipalPhone() {
		return companyPricipalPhone;
	}

	public void setCompanyPricipalPhone(String companyPricipalPhone) {
		this.companyPricipalPhone = companyPricipalPhone;
	}

	public String getCompanyPricipal() {
		return companyPricipal;
	}

	public void setCompanyPricipal(String companyPricipal) {
		this.companyPricipal = companyPricipal;
	}

	public String getCompanyImg() {
		return companyImg;
	}

	public void setCompanyImg(String companyImg) {
		this.companyImg = companyImg;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getCompanyOpenDate() {
		return companyOpenDate;
	}

	public void setCompanyOpenDate(Date companyOpenDate) {
		this.companyOpenDate = companyOpenDate;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getCompanyLongitude() {
		return companyLongitude;
	}

	public void setCompanyLongitude(String companyLongitude) {
		this.companyLongitude = companyLongitude;
	}

	public String getCompanyLatitude() {
		return companyLatitude;
	}

	public void setCompanyLatitude(String companyLatitude) {
		this.companyLatitude = companyLatitude;
	}

	public String getCompanyDes() {
		return companyDes;
	}

	public void setCompanyDes(String companyDes) {
		this.companyDes = companyDes;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
