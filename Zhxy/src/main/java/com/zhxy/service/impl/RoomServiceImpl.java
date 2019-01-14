package com.zhxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.mapper.RoomMapper;
import com.zhxy.service.RoomService;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomMapper roomMapper;
	
	@Override
	public int roomnum(int type) {
		// TODO Auto-generated method stub
		return roomMapper.roomNumByType(type);
	}

	@Override
	public int roomnumByFun(int type) {
		// TODO Auto-generated method stub
		return roomMapper.roomNumByFun(type);
	}

	@Override
	public int roomBigNum() {
		// TODO Auto-generated method stub
		int inclass=roomMapper.roomNumByFun(1);
		int study=roomMapper.roomNumByFun(2);
		return inclass>study?inclass:study;
	}

}
