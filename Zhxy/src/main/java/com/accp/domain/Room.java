package com.accp.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 教室类，并提供所需的相应数据
 */
public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	public static int CLASSROOM=0;//教室
	public static int STUDYROOM=1;//自习室
	public static int MACHINEROOM=2;//机房
	public static int ALLROOM=-1;//所有
	
	/**
	 * 表示教室能够上课
	 */
	public static int INCLASS=1;
	/**
	 * 表示教室能够自习
	 */
	public static int FORSTUDY=2;

	/**
	 * 表示教室能够考试
	 */
	public static int FORTEST=3;
	
	private int id;
	private String name;
	private int floor;
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
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
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
	
	public Room() {
		this.am=new Plan();
		this.pm=new Plan();
	}
}
