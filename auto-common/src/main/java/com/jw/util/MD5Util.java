package com.jw.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Shiro MD5+盐值散列
 * @author Zeng
 *
 */
public class MD5Util {
	
	/**
	 * 密码加密成MD5字符串
	 * @param password 要加密的密码
	 * @param salt 盐
	 * @return 加密后的字符串
	 */
	public static String getMD5(String password, String salt) {
		
		Md5Hash md5Hash = new Md5Hash(password, salt, 5);
		
		return md5Hash.toString();
	}
	
	public static void main(String[] args) {
		Md5Hash md5Hash = new Md5Hash("123456", "17370134021", 5);
		
		System.out.println(md5Hash.toString());
	}
	
}
