package com.zhxy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zhxy.domain.People;
import com.zhxy.mapper.PeopleMapper;
import com.zhxy.service.PeopleService;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService{

	@Autowired
	PeopleMapper peopleMapper;
	
	@Override
	public List<Integer> position(People people) {
		// TODO Auto-generated method stub
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(people.getId());
		List<People> list=peopleMapper.position(people.getId());
		for (People temp : list) {
			ids.add(temp.getId());
			for (People tempPeople : temp.getPeoples()) {
				ids.add(tempPeople.getId());
			}
		}
		return ids;
	}

	@Override
	public People queryById(int id) {
		// TODO Auto-generated method stub
		return peopleMapper.queryById(id);
	}

}
