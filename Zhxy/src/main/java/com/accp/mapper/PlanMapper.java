package com.accp.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.accp.domain.Plan;

@Mapper
public interface PlanMapper {

	Plan queryById(int id);
	
}
