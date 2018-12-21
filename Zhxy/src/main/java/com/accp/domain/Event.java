package com.accp.domain;

import java.util.Date;

public class Event {

	public static int All_DAY = 0;// 整天
	public static int AM = 1;// 上午
	public static int PM = 2;// 下午

	private int id;
	private String content;
	private boolean great;
	private String note;
	private Date date;
	private int ap;
	private Room room;
	private boolean aP;
	private boolean study;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isGreat() {
		return great;
	}

	public void setGreat(boolean great) {
		this.great = great;
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int ap) {
		this.ap = ap;
		if (ap > 0) {
			this.aP = ap == PM;
		}
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean isaP() {
		return aP;
	}

	public void setaP(boolean aP) {
		this.aP = aP;
	}
}
