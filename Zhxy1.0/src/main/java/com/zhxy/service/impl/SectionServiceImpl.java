package com.zhxy.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxy.domain.Section;
import com.zhxy.mapper.SectionMapper;
import com.zhxy.service.SectionService;

@Service
@Transactional
public class SectionServiceImpl implements SectionService{

	@Autowired
	SectionMapper sectionMapper;
	
	@Override
	public void addSection(List<Section> section) {
		// TODO Auto-generated method stub
		sectionMapper.addSection(section);
	}

	@Override
	public void deleteSec(List<Section> list) {
		// TODO Auto-generated method stub
		sectionMapper.deleteSec(list);
	}

	@Override
	public void updateSec(Section section) {
		// TODO Auto-generated method stub
		sectionMapper.updateSec(section);
	}

	@Override
	public void updateSec(List<Section> sections) {
		// TODO Auto-generated method stub
		for (Section section : sections) {
			sectionMapper.updateSec(section);
		}
	}

}
