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
	private String apStr;
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
		this.apStr=ap?"下午":"上午";
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
	public String getApStr() {
		return apStr;
	}
	public void setApStr(String apStr) {
		this.apStr = apStr;
	}
	
	public Plan() {
		this.clazz=new Clazz();
	}
}
