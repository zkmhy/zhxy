package com.zhxy.domain;

import java.io.Serializable;
import java.util.Date;

public class Plan implements Serializable{

	/**
	 * 
	 */
	public static boolean AM=false;
	public static boolean PM=true;
	
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean ap; // 上午/下午
	private int rid;
	private Clazz clazz;
	private boolean active; //是否活动
	private boolean study; //是否上课
	private Event event;
	private Date date;
	private Room room;
	private boolean advance;
	
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
		this.advance=false;
		this.room=new Room();
	}
	public Plan(Clazz clazz, Room room, Date tempDate, boolean ap, boolean study, boolean active) {
		// TODO Auto-generated constructor stub
		this.clazz=clazz;
		this.rid=room.getId();
		this.date=tempDate;
		this.ap=ap;
		this.study=study;
		this.active=active;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room==null?new Room():room;
	}
	public boolean isAdvance() {
		return advance;
	}
	public void setAdvance(boolean advance) {
		this.advance = advance;
	}
	
}
