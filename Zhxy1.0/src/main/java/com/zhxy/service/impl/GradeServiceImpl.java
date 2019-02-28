package com.zhxy.service.impl;

import java.util.ArrayList;
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
	public List<Grade> grade(People people,String date) {
		// TODO Auto-generated method stub
		List<Integer> ids=peopleService.position(people);
		List<Grade> grades=gradeMapper.findGrade(ids);
		List<Grade> needGrades=new ArrayList<Grade>();
		for (Grade grade : grades) {
			grade.setClazzs(clazzService.clazz(ids, grade.getId() ,date));			
		}
		for (Grade grade : grades) {
			if(grade.getClazzs().size()>0) {
				needGrades.add(grade);
			}
		}
		return needGrades;
	}

	@Override
	public List<Grade> queryGrades(int mid) {
		// TODO Auto-generated method stub
		return gradeMapper.grades(mid);
	}

	@Override
	public List<Grade> allGrade() {
		// TODO Auto-generated method stub
		return gradeMapper.allGrade();
	}

	@Override
	public Grade queryById(int id) {
		// TODO Auto-generated method stub
		return gradeMapper.queryById(id);
	}

	@Override
	public boolean existGrade() {
		// TODO Auto-generated method stub
		return gradeMapper.existGrade();
	}

	@Override
	public List<Grade> gradeList() {
		// TODO Auto-generated method stub
		return gradeMapper.gradeList();
	}

}
