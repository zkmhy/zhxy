package com.accp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 帮助类
 * @author 晨曦
 * @since 
 */
public class MyUtils {

	static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 根据date获取date日期这周的周一
	 * @param date
	 * @return
	 */
	public static Date thisWeekMonday(Date date) {
		Calendar dateC=Calendar.getInstance();
		dateC.setTime(date);
		int i=dateC.get(Calendar.DAY_OF_WEEK);
		if(i==1) {
			dateC.add(Calendar.DAY_OF_MONTH, -1);
		}
		int day=dateC.get(Calendar.DAY_OF_WEEK);
		dateC.setFirstDayOfWeek(Calendar.MONDAY);
		dateC.add(Calendar.DATE, dateC.getFirstDayOfWeek()-day);
		return dateC.getTime();
	}
	
	/**
	 * 根据date获取date日期下周的周一
	 * @param date
	 * @return
	 */
	public static Date nextWeekMonday(Date date) {
		Calendar dateC=Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, 7);
		return dateC.getTime();
	}
	
	/**
	 * 根据date获取date日期上周的周一
	 * @param date
	 * @return
	 */
	public static Date lastWeekMonday(Date date) {
		Calendar dateC=Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, -7);
		return dateC.getTime();
	}
		
}
