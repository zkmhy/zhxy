package com.zhxy.domain;

import java.util.Date;
import java.util.List;

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
	private List<People> peoples;
	private String peoplesStr;

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

	public List<People> getPeoples() {
		return peoples;
	}

	public void setPeoples(List<People> peoples) {
		this.peoples = peoples;
		StringBuffer sb=new StringBuffer();
		for (People people : peoples) {
			sb.append(people.getName()+" / ");
		}
		this.peoplesStr=sb.substring(0, sb.lastIndexOf("/"));
	}

	public String getPeoplesStr() {
		return peoplesStr;
	}

	public void setPeoplesStr(String peoplesStr) {
		this.peoplesStr = peoplesStr;
	}
}
