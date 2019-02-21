package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Curriculum;
import com.zhxy.domain.Section;

public interface CurrService {

	List<Curriculum> queryCurriculums(int mid,int gid);
	
	List<Curriculum> curriculums(Integer mid,Integer gid);
	
	Curriculum curriculum(Integer id);
	
	void addSection(List<Section> list);
	
	void addCurr(Curriculum curriculum);
	
	void deleteSec(Curriculum curriculum);
	
	void deleteCurr(Integer[] list);
	
	void updateCurr(Curriculum curriculum);
	
	List<Curriculum> restCurr(Integer vid,Integer mid,Integer gid);
	
	List<Curriculum> curriculums(Integer vid);
}
