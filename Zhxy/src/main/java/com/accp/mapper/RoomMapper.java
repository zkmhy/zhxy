package com.accp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.accp.domain.Room;

@Mapper
public interface RoomMapper {

	Room queryById(int id);
	
	int roomNum(int floor);
	
	List<Room> roomFloor(@Param("date")Date date,@Param("floor")int floor);
}
