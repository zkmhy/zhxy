package com.accp.domain;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean ap;
	private Clazz clazz;
	private boolean active;
	private boolean study;
	private Room room;
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAp() {
		return ap;
	}
	public void setAp(boolean ap) {
		this.ap = ap;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
		if(active) {
			this.clazz.getCurr().setName("班级活动");
		}
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isStudy() {
		return study;
	}
	public void setStudy(boolean study) {
		this.study = study;
	}
	
	public Plan() {
		this.clazz=new Clazz();
	}
}
