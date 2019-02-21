package com.zhxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Section;

@Mapper
public interface SectionMapper {

	List<Section> restSection(@Param("vid")int vid,@Param("cid")int cid);

	List<Section> c_s(@Param("cid")Integer cid,@Param("vid")Integer vid);
	
	void addSection(List<Section> list);
	
	void deleteSec(List<Section> list);
	
	void updateSec(Section section);
}
