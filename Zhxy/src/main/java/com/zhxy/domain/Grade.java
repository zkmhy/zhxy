package com.zhxy.domain;

import java.io.Serializable;
import java.util.List;

public class Grade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<Clazz> clazzs;
	
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
}
