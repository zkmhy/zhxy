package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Room;

public interface RoomService {

	/**
	 * 该方法暂时不用，等待完善
	 * @param type
	 * @return
	 */
	int roomnum(int type);
	
	int roomnumByFun(int type);
	
	int roomBigNum();
	
	List<Room> spare(String date,int id,boolean study,boolean ap);
	
	Room queryById(int id);
}
