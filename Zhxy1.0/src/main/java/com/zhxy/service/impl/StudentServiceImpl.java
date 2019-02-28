package com.zhxy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxy.domain.Student;
import com.zhxy.mapper.StudentMapper;
import com.zhxy.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentMapper studentMapper;
	
	@Override
	public boolean existStudent(Integer mid) {
		// TODO Auto-generated method stub
		return studentMapper.existStudent(mid);
	}

	@Override
	public List<Student> students(int page, List<Student> list) {
		// TODO Auto-generated method stub
		List<Student> temps=new ArrayList<Student>();
		int start=(page-1)*10;
		int end=start+10;
		for(;start<end;start++) {
			temps.add(list.get(start));
		}
		return temps;
	}

	@Override
	public List<Student> students(Integer mid) {
		// TODO Auto-generated method stub
		return studentMapper.students(mid);
	}

	@Override
	public PageInfo<Student> querys(int num,Integer mid,  List<Student> list) {
		// TODO Auto-generated method stub
		if(list==null) {
			return null;
		}
		Page<Student> page=PageHelper.startPage(num, 10,true);
		studentMapper.querys(list, mid);
		return page.toPageInfo();
	}

	@Override
	public PageInfo<Student> frees(int num,Integer mid, List<Student> list) {
		// TODO Auto-generated method stub
		if(list==null) {
			return null;
		}
		Page<Student> pageInfo=PageHelper.startPage(num, 10,true);
		studentMapper.frees(list,mid);
		return pageInfo.toPageInfo();
	}

}
