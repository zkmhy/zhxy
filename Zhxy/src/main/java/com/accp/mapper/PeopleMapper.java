package com.accp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.accp.domain.People;

@Mapper
public interface PeopleMapper {

	People queryById(int id);
}
