package com.zhxy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Map<Integer, String> maps=new HashMap<Integer, String>();
		maps.put(people.getId(), null);
		List<People> list=peopleMapper.position(people.getId());
		for (People temp : list) {
			on(maps, temp);
		}
		List<Integer> ids=new ArrayList<>();
		for (int integer : maps.keySet()) {
			ids.add(integer);
		}
		return ids;
	}

	public void on(Map<Integer, String> maps,People people) {
		maps.put(people.getId(), null);
		for (People item : people.getPeoples()) {
			maps.put(item.getId(), null);
			if(item.getPeoples().size()>0) {
				on(maps, item);
			}
		}
	}
	
	@Override
	public People queryById(int id) {
		// TODO Auto-generated method stub
		return peopleMapper.queryById(id);
	}

}
