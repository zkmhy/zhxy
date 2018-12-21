package com.accp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.accp.domain.Clazz;

@Mapper
public interface ClazzMapper {

	Clazz queryById(int id);
	
	List<Clazz> allClazz();
}
