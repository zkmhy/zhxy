package com.accp.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.accp.utils.MyUtils;

public class Plans {

	private List<Room> lists;
	private Date date;
	private String str;
	private String week;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd");
		this.date = date;
		this.str=simpleDateFormat.format(date);
		this.week=MyUtils.weekDay(date);
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {		
		this.str = str;
	}
	
	public Plans() {
		
	}
	
	public Plans(Date date) {
		setDate(date);
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
}
