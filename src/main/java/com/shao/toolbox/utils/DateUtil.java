package com.shao.toolbox.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用工具类
 * @author ZhengBoShao
 *
 */
public class DateUtil {

	/**
	 * 日期格式转换（Date -> String）
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 日期格式转换（String -> Date）
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date formatDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}
	
}
