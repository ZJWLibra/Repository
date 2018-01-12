package com.jw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间类型转换
 * @author Zeng
 *
 */
public class DateUtil {
	
	/**
	 * String转Date
	 * @param strDate 要转换的字符串
	 * @param dateType 时间格式
	 * @return 转换后的时间
	 * @throws ParseException 
	 */
	public static Date stringToDate(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String dateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		return sdf.format(date);
	}
}
