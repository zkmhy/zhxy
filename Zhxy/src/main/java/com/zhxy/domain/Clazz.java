package com.zhxy.domain;

import java.io.Serializable;
import java.util.List;

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
	private int planNum;
	private int weekNum;
	private int num;
	private int people;
	private Plan am;
	private Plan pm;
	private List<Plan> plans;
	
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
	public int getPlanNum() {
		return planNum;
	}
	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Plan getAm() {
		return am;
	}
	public void setAm(Plan am) {
		this.am = am;
	}
	public Plan getPm() {
		return pm;
	}
	public void setPm(Plan pm) {
		this.pm = pm;
	}
	public List<Plan> getPlans() {
		return plans;
	}
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
		for (Plan plan : plans) {
			if(plan.isAp()) {
				this.pm=plan;
			}else {
				this.am=plan;
			}
		}
	}
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
}
