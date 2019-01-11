package com.accp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.accp.domain.Clazz;
import com.accp.domain.People;

@Mapper
public interface ClazzMapper {

	Clazz queryById(int id);
	
	List<Clazz> allClazz();

	List<Clazz> findClazz(@Param("teacher")People people,@Param("begin")Date date,@Param("end")Date end);

	Integer isStudyByOnself(@Param("clazz")Clazz clazz,@Param("begin")Date date,@Param("end")Date end);
}
