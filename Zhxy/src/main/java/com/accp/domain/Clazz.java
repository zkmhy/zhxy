package com.accp.domain;

import java.io.Serializable;

public class Clazz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Grade grade;
	private People teacher;
	private Curriculum curr;
	
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
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public People getTeacher() {
		return teacher;
	}
	public void setTeacher(People teacher) {
		this.teacher = teacher;
	}
	public Curriculum getCurr() {
		return curr;
	}
	public void setCurr(Curriculum curr) {
		this.curr = curr;
	}
	
	public Clazz() {
		this.name="";
		this.curr=new Curriculum();
		this.teacher=new People();
	}
}
