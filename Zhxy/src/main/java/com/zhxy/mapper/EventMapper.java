package com.zhxy.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Event;

@Mapper
public interface EventMapper {

	Event dateEvent(@Param("date")Date date,@Param("id")int id);
}
