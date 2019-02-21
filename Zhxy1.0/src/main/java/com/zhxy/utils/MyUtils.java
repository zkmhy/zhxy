package com.zhxy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhxy.domain.DatePlan;
import com.zhxy.domain.Room;

/**
 * 帮助类
 * 
 * @author 晨曦
 * @since
 */
public class MyUtils {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 根据date获取date日期这周的周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date thisWeekMonday(Date date) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(date);
		int i = dateC.get(Calendar.DAY_OF_WEEK);
		if (i == 1) {
			dateC.add(Calendar.DAY_OF_MONTH, -1);
		}
		int day = dateC.get(Calendar.DAY_OF_WEEK);
		dateC.setFirstDayOfWeek(Calendar.MONDAY);
		dateC.add(Calendar.DATE, dateC.getFirstDayOfWeek() - day);
		return dateC.getTime();
	}

	/**
	 * 根据date获取date日期这周的周末
	 * 
	 * @param date
	 * @return
	 */
	public static Date thisWeekSunday(Date date) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, 6);
		return dateC.getTime();
	}

	/**
	 * 根据date获取date日期下周的周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextWeekMonday(Date date) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, 7);
		return dateC.getTime();
	}

	/**
	 * 根据date获取date日期下周的周末
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextWeekSunday(Date date) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, 13);
		return dateC.getTime();
	}

	/**
	 * 根据date获取date日期上周的周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date lastWeekMonday(Date date) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, -7);
		return dateC.getTime();
	}

	/**
	 * 根据date获取date日期上周的周末
	 * 
	 * @param date
	 * @return
	 */
	public static Date lastWeekSunday(Date date) {
		Calendar dateC = Calendar.getInstance();
		dateC.setTime(thisWeekMonday(date));
		dateC.add(Calendar.DATE, -1);
		return dateC.getTime();
	}

	/**
	 * 根据传入日期，返回日期所在的周列表，周一 ———— 周末
	 * 
	 * @param date
	 * @return
	 */
	public static List<Date> weeks(Date date) {
		List<Date> dates = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastWeekSunday(date));
		for (int i = 0; i < 7; i++) {
			calendar.add(Calendar.DATE, 1);
			dates.add(calendar.getTime());
		}
		return dates;
	}

	/**
	 * 根据传入日期，返回日期所在的周列表，周日 ————周六
	 * 
	 * @param date
	 * @return
	 */
	public static List<Date> week(Date date) {
		List<Date> dates = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1 - calendar.get(Calendar.DAY_OF_WEEK));
		for (int i = 0; i < 7; i++) {
			dates.add(calendar.getTime());
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	/**
	 * 根据传入的日期判断是星期几
	 */
	public static String weekDay(Date date) {
		String[] strs = new String[] { "天", "一", "二", "三", "四", "五", "六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String suffix = strs[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		return "星期" + suffix;
	}

	public static Date NOW() {
		return new Date();
	}

	public static int dateSpan(Date date, Date other) {
		long i = (date.getTime() - other.getTime()) / (1000 * 60 * 60 * 24);
		i = i < 0 ? -i : i;
		return (int)i;
	}

	public static int dayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return i == 0 ? 7 : i;
	}

	public static Map<String, List<Date>> calendar(Date date) {
		Map<String, List<Date>> maps = new HashMap<>();
		Date tempDate = dateOfMonthFirstDay(date);
		Date dateOfMonthEndDay = dateOfMonthEndDay(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfMonthEndDay);
		int i = calendar.get(Calendar.WEEK_OF_MONTH);
		for (int j = 1; j <= i; j++) {
			List<Date> weeks = week(tempDate);
			maps.put(j + "", weeks);
			calendar.setTime(weeks.get(0));
			calendar.add(Calendar.DATE, 7);
			tempDate = calendar.getTime();
		}
		return maps;
	};

	public static Date dateOfMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int spanOfMonthFirstDay = 1 - calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, spanOfMonthFirstDay);
		return calendar.getTime();
	}

	public static Date dateOfMonthEndDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		Date temp = dateOfMonthFirstDay(calendar.getTime());
		calendar.setTime(temp);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	public static void findAnother(Map<String, List<DatePlan>> maps, int month) {
		Calendar calendar = Calendar.getInstance();
		for (String string : maps.keySet()) {
			for (DatePlan datePlan : maps.get(string)) {
				calendar.setTime(datePlan.getDate());
				int i = calendar.get(Calendar.MONTH);
				datePlan.setAnother(i != month);
			}
		}
	}

	public static Date parse(String string) {
		try {
			return sdf.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("日期格式错误 yyyy-MM-dd");
			return new Date();
		}
	}

	public static String format(Date date) {
		if(date==null) {
			return null;
		}
		return sdf.format(date);
	}

	public static boolean isEmpty(String string) {
		if (string == null || string.trim().equals("") || string.trim().equals(" ")
				|| string.toLowerCase().equals("null")) {
			return true;
		}
		return false;
	}

	public static String calendarTitle(int type, Date date) {
		String title = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (type) {
		case 1:
			String[] months = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
			int month = calendar.get(Calendar.MONTH);
			title = months[month] + "月 " + calendar.get(Calendar.YEAR);
			break;
		case 2:
			calendar.add(Calendar.DATE, 1 - (calendar.get(Calendar.DAY_OF_WEEK)));
			Date first = calendar.getTime();
			calendar.setTime(first);
			int year = calendar.get(Calendar.YEAR);
			calendar.add(Calendar.DATE, 6);
			Date last = calendar.getTime();
			boolean isTrue = isAnotherMonth(first, last);
			boolean isYear = isAnotherYear(first, last);
			String firstStr = !isYear ? simpleDateFormat.format(first) + " " + year + "年"
					: simpleDateFormat.format(first);
			String lastStr = !isTrue ? simpleDateFormat.format(last) : calendar.get(Calendar.DATE) + "日";
			title = firstStr + " —— " + lastStr + " " + calendar.get(Calendar.YEAR) + "年";
			break;
		case 3:
			String week = weekDay(date);
			String monthStr = simpleDateFormat.format(date);
			title = week + "," + monthStr + "," + calendar.get(Calendar.YEAR);
			break;
		}
		return title;
	}

	public static String preDate(int type, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (type) {
		case 1:
			calendar.add(Calendar.MONTH, -1);
			break;
		case 2:
			calendar.add(Calendar.DATE, -7);
			break;
		case 3:
			calendar.add(Calendar.DATE, -1);
			break;
		}
		return sdf.format(calendar.getTime());
	}

	public static String nextDate(int type, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (type) {
		case 1:
			calendar.add(Calendar.MONTH, 1);
			break;
		case 2:
			calendar.add(Calendar.DATE, 7);
			break;
		case 3:
			calendar.add(Calendar.DATE, 1);
			break;
		}
		return sdf.format(calendar.getTime());
	}

	public static boolean isToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(NOW());
		int nowYear = calendar.get(Calendar.YEAR);
		int nowDay = calendar.get(Calendar.DAY_OF_YEAR);
		return year == nowYear && day == nowDay;
	}

	public static boolean isAnotherMonth(Date one, Date two) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(one);
		int oneYear = calendar.get(Calendar.YEAR);
		int oneMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(two);
		int twoYear = calendar.get(Calendar.YEAR);
		int twoMonth = calendar.get(Calendar.MONTH);
		return oneYear == twoYear && oneMonth == twoMonth;
	}

	public static boolean isAnotherYear(Date one, Date two) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(one);
		int oneYear = calendar.get(Calendar.YEAR);
		calendar.setTime(two);
		int twoYear = calendar.get(Calendar.YEAR);
		return oneYear == twoYear;
	}

	public static Map<String, List<Date>> fill(Date one,Date another) {
		Map<String, List<Date>> maps=new HashMap<>();
		boolean isMax=one.getTime()>another.getTime();
		Date max=isMax?one:another;
		Date min=isMax?another:one;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(min);
		int dayofweek=calendar.get(Calendar.DAY_OF_WEEK);
		int interger=0;
		while (true) {
			List<Date> lists=new ArrayList<>();
			if(dayofweek!=2) {
				for (int day = 0; day < dayofweek-2; day++) {
					lists.add(null);
				}
			}
			for (int i = 0; i <= 8-dayofweek; i++) {
				lists.add(calendar.getTime());				
				calendar.add(Calendar.DATE, 1);
				if(calendar.getTime().getTime()>max.getTime()) {
					maps.put(interger+"", lists);
					return maps;
				}
			}
			maps.put(interger+"", lists);
			dayofweek=2;
			interger++;
		}
	}
	
	public static boolean contains(List<Room> list,int id) {
		for (Room room : list) {
			if(room.getId()==id) {
				return true;
			}
		}
		return false;
	}
	
	public static void removeKey(List<Room> list,int id) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==id) {
				list.remove(i);
			}
		}
	}

}
