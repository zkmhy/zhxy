package com.accp.domain;

import java.text.SimpleDateFormat;
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
	/**
	 * 该日期内的上课教室安排
	 */
	private List<Room> lists;
	/**
	 * 该日期内的自习教室安排
	 */
	private List<Room> studys;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd");
		this.str=simpleDateFormat.format(date);
		this.week=MyUtils.weekDay(date);
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {		
		this.str = str;
	}
	
	public List<Room> getLists() {
		return lists;
	}
	public void setLists(List<Room> lists) {
		this.lists = lists;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}

	public List<Room> getStudys() {
		return studys;
	}

	public void setStudys(List<Room> studys) {
		this.studys = studys;
		this.lists = MyUtils.fillList(lists, studys.size());		
	}
}
