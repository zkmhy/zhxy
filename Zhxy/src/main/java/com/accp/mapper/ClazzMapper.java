package com.accp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.accp.domain.Clazz;

@Mapper
public interface ClazzMapper {

	Clazz queryById(int id);
}
