package com.accp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accp.mapper.RoomMapper;
import com.accp.service.RoomService;

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

}
