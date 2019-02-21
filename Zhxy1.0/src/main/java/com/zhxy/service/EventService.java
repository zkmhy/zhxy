package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Event;
import com.zhxy.domain.InsertEvent;

public interface EventService {

	void addEvent(InsertEvent event);
	
	List<Event> all();
}
