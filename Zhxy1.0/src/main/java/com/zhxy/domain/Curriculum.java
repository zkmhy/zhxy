package com.zhxy.domain;

import java.io.Serializable;
import java.util.List;

public class Curriculum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String ename;
	private Grade grade;
	private Major major;
	private int hour;
	private int num;
	private int vhour;
	private int vnum;
	private int resthour;
	private boolean check;
	private List<Section> lists;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		if(lists!=null) {
			for (Section section : lists) {
				section.setCid(id);
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public Curriculum() {
		this.name="";
	}
	public List<Section> getLists() {
		return lists;
	}
	public void setLists(List<Section> lists) {
		this.lists = lists;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getVhour() {
		return vhour;
	}
	public void setVhour(int vhour) {
		this.vhour = vhour;
	}
	public int getVnum() {
		return vnum;
	}
	public void setVnum(int vnum) {
		this.vnum = vnum;
	}
	public int getResthour() {
		return resthour;
	}
	public void setResthour(int resthour) {
		this.resthour = resthour;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
}
