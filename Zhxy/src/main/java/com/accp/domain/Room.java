package com.accp.domain;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
