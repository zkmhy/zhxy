package com.accp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accp.domain.Plans;
import com.accp.mapper.PlanMapper;
import com.accp.mapper.RoomMapper;
import com.accp.service.PlanService;
import com.accp.utils.MyUtils;

@Service
@Transactional
public class PlanServiceImpl implements PlanService{

	@Autowired
	PlanMapper planMapper;
	
	@Autowired
	RoomMapper roomMapper;
	
	@Override
	public List<Plans> weekPlan(Date date,int floor) {
		// TODO Auto-generated method stub
		List<Plans> list=new ArrayList<Plans>();
		List<Date> dates=MyUtils.weeks(date);
		for (Date itemDate : dates) {
			Plans plans=new Plans(itemDate);
			plans.setLists(roomMapper.roomFloor(itemDate, floor));
			list.add(plans);
		}
		return list;
	}

}
