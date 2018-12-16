package com.accp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.accp.domain.Grade;

@Mapper
public interface GradeMapper {

	Grade queryById(int id);
}
