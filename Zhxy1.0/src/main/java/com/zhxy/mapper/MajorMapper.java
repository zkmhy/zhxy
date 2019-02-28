package com.zhxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Major;

@Mapper
public interface MajorMapper {

	List<Major> majors(@Param("all")boolean all);
	
	Major queryById(int id);
}
