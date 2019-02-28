package com.zhxy.domain;

import java.io.Serializable;
import java.util.List;

public class Grade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int hour;
	private String name;
	private int major;
	private List<Clazz> clazzs;
	private List<String> names;
	private List<Curriculum> lists;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Clazz> getClazzs() {
		return clazzs;
	}
	public void setClazzs(List<Clazz> clazzs) {
		this.clazzs = clazzs;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public List<Curriculum> getLists() {
		return lists;
	}
	public void setLists(List<Curriculum> lists) {
		this.lists = lists;
		this.hour=0;
		for (Curriculum curriculum : lists) {
			this.hour+=curriculum.getVhour();
		}
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
}
