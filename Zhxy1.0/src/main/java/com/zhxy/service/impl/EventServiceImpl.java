package com.zhxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.domain.Event;
import com.zhxy.domain.InsertEvent;
import com.zhxy.mapper.EventMapper;
import com.zhxy.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	EventMapper eventMapper;

	@Override
	public void addEvent(InsertEvent event) {
		// TODO Auto-generated method stub
		eventMapper.addEvent(event);
	}

	@Override
	public List<Event> all() {
		// TODO Auto-generated method stub
		return eventMapper.allEvent();
	}
}
