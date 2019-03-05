package com.zhxy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.People;
import com.zhxy.domain.Room;

@Mapper
public interface PeopleMapper {

	People queryById(int id);
	
	int classNum(@Param("people")People people,@Param("date")Date date);

	List<People> room(@Param("room")Room room,@Param("begin")Date begin,@Param("end")Date end);

	List<People> allTeacher();
	
	Integer[] floors(People people);
	
	Integer isPlanBusy(@Param("teacher")People people,@Param("day")Date day,@Param("ap")boolean ap);

	Integer isEventBusy(@Param("teacher")People people,@Param("day")Date day,@Param("ap")boolean ap);
	
	List<People> position(int id);
	
	People betterClazz();
	
	boolean existBan();
	
	boolean existTeacher(@Param("vid")int vid,@Param("gid")int gid,@Param("mid")Integer mid);
	
	List<People> bans();
	
	List<People> teacher(int cid);
	
	List<People> queryTeacher(int cid);
	
	void updateCurr(@Param("cid")int cid,@Param("list")Integer[] lists);
}
