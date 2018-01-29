package com.jw.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jw.bean.ext.UserExt;

public class ShiroUtil {
	
	/**
	 * 获取用户身份信息
	 * @return
	 */
	public static UserExt getUser() {
		Subject subject = SecurityUtils.getSubject();
		// 获取身份信息
		UserExt userExt = (UserExt) subject.getPrincipal();
		return userExt;
	}
	
}
