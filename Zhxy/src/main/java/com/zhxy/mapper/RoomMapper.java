package com.zhxy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.People;
import com.zhxy.domain.Room;

@Mapper
public interface RoomMapper {

	Room queryById(int id);
	
	int roomNumByType(int type);
	
	int roomNumByFun(@Param("type")int type); // 根据教室类型(可上课，自习，考试)  查找教室数量
	
	List<Room> datePlan(@Param("date")Date date,@Param("t")int type); // 根据日期 ， 教室类型(可上课，自习，考试) 查找教室的课表安排	
	
	List<Room> allroom();
	
	List<Room> classRoom(@Param("clazz")Clazz clazz,@Param("study")boolean study,@Param("day")Date date);

	Room teacherRoom(@Param("day")Date day, @Param("teacher")People teacher,@Param("clazz")Clazz clazz,@Param("study")boolean study);
	
	List<Room> remainRoom(@Param("day")Date day,@Param("clazz")Clazz clazz,@Param("study")boolean study);

}
