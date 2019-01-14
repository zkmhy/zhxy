package com.zhxy.domain;

import java.util.List;

public class InsertEvent extends Event {
	
	private int time;
	
	private boolean study;

	private List<Integer> idList;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isStudy() {
		return study;
	}

	public void setStudy(boolean study) {
		this.study = study;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

}
