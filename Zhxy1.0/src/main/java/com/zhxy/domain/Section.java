package com.zhxy.domain;

public class Section {

	private int id;
	private int cid;
	private String name;
	private String info;
	private int hour;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		if(name.length()>14) {
			this.info=name.substring(0,14)+"...";
		}else {
			this.info=name;
		}
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
}
