package com.zhxy.domain;

import java.util.List;

public class Major {

	private int id;
	
	private String name;
	
	private int hour;
	
	private List<Grade> lists;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public List<Grade> getLists() {
		return lists;
	}

	public void setLists(List<Grade> lists) {
		this.lists = lists;
		this.hour=0;
		for (Grade grade : lists) {
			this.hour+=grade.getHour();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
