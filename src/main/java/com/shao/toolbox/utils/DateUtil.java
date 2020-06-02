package com.shao.toolbox.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	/**
	 * 根据年月获取当月天数
	 * @param ym 年月字符串（yyyyMM）
	 * @return 当月天数
	 * @throws ParseException
	 */
	public static int getDayByYM(String ym) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(ym + "01"));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
}
