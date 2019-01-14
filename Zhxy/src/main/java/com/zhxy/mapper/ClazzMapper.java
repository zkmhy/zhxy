package com.zhxy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.People;

@Mapper
public interface ClazzMapper {

	Clazz queryById(int id);
	
	List<Clazz> allClazz();

	List<Clazz> findClazz(@Param("teacher")People people,@Param("begin")Date begin,@Param("end")Date end,@Param("before")Date before);

	Integer isStudyByOnself(@Param("clazz")Clazz clazz,@Param("begin")Date date,@Param("end")Date end);
	
	List<Clazz> queryClazz(People people);
	
	List<Clazz> clazz(People people);
}
