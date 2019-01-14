package com.zhxy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zhxy.domain.Curriculum;

@Mapper
public interface CurriculumMapper {

	Curriculum queryById(int id);
}
