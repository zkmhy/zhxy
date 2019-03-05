package com.zhxy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.domain.Curriculum;
import com.zhxy.domain.People;
import com.zhxy.mapper.PeopleMapper;
import com.zhxy.service.CurrService;
import com.zhxy.service.PeopleService;
import com.zhxy.service.VersionService;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService{

	@Autowired
	PeopleMapper peopleMapper;
	@Autowired
	VersionService versionService;
	@Autowired
	CurrService currService;
	
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

	@Override
	public People butterTeacher() {
		// TODO Auto-generated method stub
		return peopleMapper.betterClazz();
	}

	@Override
	public boolean existBan() {
		// TODO Auto-generated method stub
		return peopleMapper.existBan();
	}

	@Override
	public List<People> bans() {
		// TODO Auto-generated method stub
		return peopleMapper.bans();
	}

	@Override
	public boolean existTeacher(int gid, Integer mid) {
		// TODO Auto-generated method stub
		int vid=versionService.nowId();
		List<Curriculum> list=currService.versionCurr(vid, gid, mid);
		for (Curriculum curriculum : list) {
			if(curriculum.getTeacher()==null ||curriculum.getTeacher().size()==0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<People> teachers(int cid) {
		// TODO Auto-generated method stub
		return peopleMapper.queryTeacher(cid);
	}

	@Override
	public List<People> teachers(int cid, Integer[] lists) {
		// TODO Auto-generated method stub
		currService.delCurr(cid);
		if(lists==null || lists.length<0) {
			return peopleMapper.queryTeacher(cid);
		}
		peopleMapper.updateCurr(cid, lists);;
		return peopleMapper.queryTeacher(cid);
	}

}
