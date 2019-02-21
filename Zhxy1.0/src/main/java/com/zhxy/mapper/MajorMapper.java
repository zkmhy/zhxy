package com.zhxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxy.domain.Major;

@Mapper
public interface MajorMapper {

	List<Major> majors();
}
