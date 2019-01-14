package com.zhxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxy.domain.Grade;


@Mapper
public interface GradeMapper {

	Grade queryById(int id);
	
	List<Grade> findGrade(int id);
}
