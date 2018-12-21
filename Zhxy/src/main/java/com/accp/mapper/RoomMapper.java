package com.accp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.accp.domain.Clazz;
import com.accp.domain.Room;

@Mapper
public interface RoomMapper {

	Room queryById(int id);
	
	int roomNumByType(int type);
	
	int roomNumByFun(@Param("type")int type); // 根据教室类型(可上课，自习，考试)  查找教室数量
	
	List<Room> datePlan(@Param("date")Date date,@Param("type")int type); // 根据日期 ， 教室类型(可上课，自习，考试) 查找课表安排

	List<Room> allRoom(@Param("type")int type,@Param("clazz")Clazz clazz); // 根据楼层，教室类型(可上课，自习，考试) 查找教室
	
	Integer[] floors(int id); // 根据班级编号 查找 可去楼层
	
	List<Room> classRoom(@Param("clazz")Clazz clazz,@Param("type")int type); // 根据班级，教室功能 查找所需教室

	/**
	 * 查询对应时间段，对应班级  可供使用的教室数量
	 * @param date 日期
	 * @param clazz 班级
	 * @param ap 上/下午
	 * @param type 教室类型
	 * @return 返回可用教室数量
	 */
	List<Room> remainingRoom(@Param("date")Date date,@Param("clazz")Clazz clazz,@Param("ap")boolean ap,@Param("type")int type);

}
