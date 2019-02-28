package com.zhxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.domain.Major;
import com.zhxy.mapper.MajorMapper;
import com.zhxy.service.MajorService;

@Service
@Transactional
public class MajorServiceImpl implements MajorService{

	@Autowired
	MajorMapper majorMapper;
	
	@Override
	public List<Major> majors(boolean all) {
		// TODO Auto-generated method stub
		return majorMapper.majors(all);
	}

	@Override
	public Major queryById(int id) {
		// TODO Auto-generated method stub
		return majorMapper.queryById(id);
	}

}
