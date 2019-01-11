package com.accp.domain;

import java.util.List;

public class ClassPlan {
	
	private Clazz clazz;

	private Plan am;
	
	private Plan pm;
	
	private List<Plan> lists;

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

	public List<Plan> getLists() {
		return lists;
	}

	public void setLists(List<Plan> lists) {
		this.lists = lists;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
}
