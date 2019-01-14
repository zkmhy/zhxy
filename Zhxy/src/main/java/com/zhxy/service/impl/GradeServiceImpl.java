package com.zhxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.domain.Grade;
import com.zhxy.domain.People;
import com.zhxy.mapper.GradeMapper;
import com.zhxy.service.ClazzService;
import com.zhxy.service.GradeService;
import com.zhxy.service.PeopleService;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{

	@Autowired
	GradeMapper gradeMapper;
	@Autowired
	PeopleService peopleService;
	@Autowired
	ClazzService clazzService;
	
	@Override
	public List<Grade> grade(People people) {
		// TODO Auto-generated method stub
		List<Integer> ids=peopleService.position(people);
		List<Grade> grades=gradeMapper.findGrade(ids);
		for (Grade grade : grades) {
			grade.setClazzs(clazzService.clazz(ids, grade.getId()));
		}
		return gradeMapper.findGrade(ids);
	}

}
