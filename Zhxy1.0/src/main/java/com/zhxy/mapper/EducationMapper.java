package com.zhxy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zhxy.domain.Education;

@Mapper
public interface EducationMapper {

	Education queryById(int id);
}
