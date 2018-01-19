package com.jw.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jw.common.AutoModel;

public class User extends AutoModel implements Serializable {
	private static final long serialVersionUID = -1992178596531639733L;

	private String userId;

	private String userEmail;

	private String userPhone;

	private String userPwd;

	private String userSalt;

	private String userNickname;

	private String userIdentity;

	private String userName;

	private String userGender;

	private Date userBirthday;

	private String userAddress;

	private String qqOpenId;

	private String weiboOpenId;

	private String wechatOpenId;

	private String userIcon;

	private String userDes;

	private String companyId;

	private Double userSalary;

	private Date userCreatedTime;

	private Date userLoginedTime;

	private String userStatus;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getWeiboOpenId() {
		return weiboOpenId;
	}

	public void setWeiboOpenId(String weiboOpenId) {
		this.weiboOpenId = weiboOpenId;
	}

	public String getWechatOpenId() {
		return wechatOpenId;
	}

	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getUserDes() {
		return userDes;
	}

	public void setUserDes(String userDes) {
		this.userDes = userDes;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Double getUserSalary() {
		return userSalary;
	}

	public void setUserSalary(Double userSalary) {
		this.userSalary = userSalary;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUserCreatedTime() {
		return userCreatedTime;
	}

	public void setUserCreatedTime(Date userCreatedTime) {
		this.userCreatedTime = userCreatedTime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUserLoginedTime() {
		return userLoginedTime;
	}

	public void setUserLoginedTime(Date userLoginedTime) {
		this.userLoginedTime = userLoginedTime;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}