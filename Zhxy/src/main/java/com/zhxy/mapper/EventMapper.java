package com.zhxy.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Event;
import com.zhxy.domain.InsertEvent;

@Mapper
public interface EventMapper {

	Event dateEvent(@Param("date")Date date,@Param("id")int id);
	
	int addEvent(InsertEvent event);
}
