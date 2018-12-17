package com.accp.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	 * 根据date获取date日期这周的周末
	 * @param date
	 * @return
	 */
	public static Date thisWeekSunday(Date date) {
		Calendar dateC=Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, 6);
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
	 * 根据date获取date日期下周的周末
	 * @param date
	 * @return
	 */
	public static Date nextWeekSunday(Date date) {
		Calendar dateC=Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, 13);
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
	/**
	 * 根据date获取date日期上周的周末
	 * @param date
	 * @return
	 */
	public static Date lastWeekSunday(Date date) {
		Calendar dateC=Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, -1);
		return dateC.getTime();
	}
	
	/**
	 * 根据传入日期，返回日期所在的周列表，周一 ———— 周末
	 * @param date
	 * @return
	 */
	public static List<Date> weeks(Date date) {
		List<Date> dates=new ArrayList<>();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(lastWeekSunday(date));
		for (int i = 0; i < 7; i++) {
			calendar.add(Calendar.DATE, 1);
			dates.add(calendar.getTime());
		}
		return dates;
	}
	
	/**
	 * 根据传入的日期判断是星期几
	 */
	public static String weekDay(Date date) {
		String[] strs=new String[]{"天","一","二","三","四","五","六"};
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		String suffix=strs[calendar.get(Calendar.DAY_OF_WEEK)-1];
		return "星期"+suffix;
	}
	
}
