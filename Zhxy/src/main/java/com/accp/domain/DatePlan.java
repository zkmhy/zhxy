package com.accp.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.accp.utils.MyUtils;

/**
 * 日期安排类，在某个日期的课程安排总情况
 * @author 晨曦
 *
 */
public class DatePlan {

	/**
	 * 日期
	 */
	private Date date;
	/**
	 * MM-dd格式的日期
	 */
	private String str;
	/**
	 * 日期对应的星期几
	 */
	private String week;
	
	private int num;
	
	private Boolean another;
	
	private boolean today;
	
	private String dateStr;
	
	private List<Clazz> plans;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd");
		this.str=simpleDateFormat.format(date);
		this.week=MyUtils.weekDay(date);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		this.num=calendar.get(Calendar.DAY_OF_MONTH);
		this.today=MyUtils.isToday(date);
		this.dateStr=MyUtils.format(date);
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {		
		this.str = str;
	}
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Boolean getAnother() {
		return another;
	}

	public void setAnother(Boolean another) {
		this.another = another;
	}

	public boolean isToday() {
		return today;
	}

	public void setToday(boolean today) {
		this.today = today;
	}

	public List<Clazz> getPlans() {
		return plans;
	}

	public void setPlans(List<Clazz> plans) {
		this.plans = plans;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}
