package com.zhxy.domain;

import java.util.Date;
import java.util.List;

public class Event {

	public static int All_DAY = 0;// 整天
	public static int AM = 1;// 上午
	public static int PM = 2;// 下午

	private int id;
	private String allcontent;
	private String content;
	private boolean great;
	private String note;
	private Date date;
	private int ap;
	private int roomid;
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
		this.allcontent=content;
		this.content = content.length()>7? content.substring(0,7)+"...":content;
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
			sb.append(people.getAllname()+" / ");
		}
		if(peoples.size()>0) {
			this.peoplesStr=sb.substring(0, sb.lastIndexOf("/"));			
			if(peoplesStr.length()>10) {
				this.peoplesStr=peoplesStr.substring(0, 10)+"...";
			}
		}
	}

	public String getPeoplesStr() {
		return peoplesStr;
	}

	public void setPeoplesStr(String peoplesStr) {
		this.peoplesStr = peoplesStr;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getAllcontent() {
		return allcontent;
	}

	public void setAllcontent(String allcontent) {
		this.allcontent = allcontent;
	}
	
}
