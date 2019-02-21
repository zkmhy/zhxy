package com.zhxy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Event;
import com.zhxy.domain.InsertEvent;
import com.zhxy.domain.People;

@Mapper
public interface EventMapper {

	Event dateEvent(@Param("date")Date date,@Param("id")int id);
	
	int addEvent(InsertEvent event);
	
	List<Event> allEvent();
	
	List<People> peoples(int id);
}
