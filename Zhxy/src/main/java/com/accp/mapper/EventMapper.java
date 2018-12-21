package com.accp.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.accp.domain.Clazz;
import com.accp.domain.Event;

@Mapper
public interface EventMapper {

	Event dateEvent(@Param("date")Date date,@Param("clazz")Clazz clazz);
}
