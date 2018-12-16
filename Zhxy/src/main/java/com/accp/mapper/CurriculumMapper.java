package com.accp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.accp.domain.Curriculum;

@Mapper
public interface CurriculumMapper {

	Curriculum queryById(int id);
}
