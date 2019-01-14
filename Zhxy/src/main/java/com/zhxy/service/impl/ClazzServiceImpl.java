package com.zhxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.People;
import com.zhxy.mapper.ClazzMapper;
import com.zhxy.service.ClazzService;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService{

	@Autowired
	ClazzMapper clazzMapper;

	@Override
	public List<Clazz> clazz(List<Integer> id) {
		// TODO Auto-generated method stub
		return clazzMapper.clazz(id, null);
	}

	@Override
	public List<Clazz> clazz(List<Integer> id, int gradeid) {
		// TODO Auto-generated method stub
		return clazzMapper.clazz(id, gradeid);
	}

	
}
