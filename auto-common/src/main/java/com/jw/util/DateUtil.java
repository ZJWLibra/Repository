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
	public static Date StringToDate(String strDate, String dateType) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(dateType);
		
		Date date = formatter.parse(strDate);
		
		return date;
	}
}
