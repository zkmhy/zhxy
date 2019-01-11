package com.accp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 教室类，并提供所需的相应数据
 */
public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private int id;
	private Boolean enable;
	private Boolean inclass;
	private Boolean forstudy;
	private Boolean fortest;
	private String name;
	private Boolean ap;
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
		this.plans=new ArrayList<Plan>();
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Boolean getInclass() {
		return inclass;
	}
	public void setInclass(Boolean inclass) {
		this.inclass = inclass;
	}
	public Boolean getForstudy() {
		return forstudy;
	}
	public void setForstudy(Boolean forstudy) {
		this.forstudy = forstudy;
	}
	public Boolean getFortest() {
		return fortest;
	}
	public void setFortest(Boolean fortest) {
		this.fortest = fortest;
	}
	public Boolean getAp() {
		return ap;
	}
	public void setAp(Boolean ap) {
		this.ap = ap;
	}
}
