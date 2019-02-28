package com.zhxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.ClazzInfo;
import com.zhxy.domain.Major;
import com.zhxy.domain.Student;
import com.zhxy.mapper.ClazzMapper;
import com.zhxy.service.ClazzService;
import com.zhxy.service.GradeService;
import com.zhxy.service.MajorService;
import com.zhxy.service.PeopleService;
import com.zhxy.service.StudentService;
import com.zhxy.utils.MyUtils;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService{

	@Autowired
	ClazzMapper clazzMapper;
	@Autowired
	MajorService majorService;
	@Autowired
	PeopleService peopleService;
	@Autowired
	GradeService gradeService;
	@Autowired
	StudentService studentService;

	@Override
	public List<Clazz> clazz(List<Integer> id) {
		// TODO Auto-generated method stub
		return clazzMapper.clazz(id, null);
	}

	@Override
	public List<Clazz> clazz(List<Integer> id, int gradeid,String date) {
		// TODO Auto-generated method stub
		return clazzMapper.notIndateClazz(id, gradeid, date);
	}

	@Override
	public List<Clazz> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClazzInfo auto(int gid,Integer mid,String date) {
		// TODO Auto-generated method stub		
		ClazzInfo clazz=new ClazzInfo();
		clazz.setName(clazzName(gid, mid));
		if(mid!=null) {
			Major major=majorService.queryById(mid);
			clazz.setMajor(major);
		}
		clazz.setGrade(gradeService.queryById(gid));
		List<Student> list=studentService.students(mid);
		clazz.setAllStudents(list);
		return clazz;
	}

	@Override
	public String clazzName(int gid, Integer mid) {
		// TODO Auto-generated method stub
		String name="";
		int num=clazzMapper.nextNum(gid,mid);
		if(mid!=null) {
			Major major=majorService.queryById(mid);
			name=MyUtils.clazzName(num,major.getName());
		}else {
			name=MyUtils.clazzName(num);
		}
		return name;
	}

	@Override
	public boolean existClazz() {
		// TODO Auto-generated method stub
		return clazzMapper.existClazz();
	}

}
