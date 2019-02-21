package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Section;

public interface SectionService {

	void addSection(List<Section> section);
	
	void deleteSec(List<Section> list);
	
	void updateSec(Section section);
	
	void updateSec(List<Section> sections);
}
