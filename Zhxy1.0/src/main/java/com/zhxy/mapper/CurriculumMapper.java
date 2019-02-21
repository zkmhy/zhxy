package com.zhxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Curriculum;
import com.zhxy.domain.Section;

@Mapper
public interface CurriculumMapper {

	Curriculum queryById(Integer id);
	
	List<Curriculum> allCurr(Integer id);
	
	List<Curriculum> curriculums(@Param("mid")int mid,@Param("gid")int gid);
	
	List<Curriculum> currInfos(@Param("mid")Integer mid,@Param("gid")Integer gid);
	
	void addSection(List<Section> list);
	
	void addCurr(Curriculum curriculum);
	
	void deleteSec(Curriculum curriculum);
	
	void deleteCurr(Integer[] list);
	
	void deleteSection(Integer[] list);
	
	void deleteSectionInfo(Integer[] list);
	
	void updateCurr(Integer[] list);
	
	void updateCurriculum(Curriculum curriculum);
	
	List<Curriculum> restCurr(@Param("vid")Integer vid,@Param("mid")Integer mid,@Param("gid")Integer gid);

}
