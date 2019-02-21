package com.zhxy.service;

import com.zhxy.domain.People;

import java.util.List;

public interface PeopleService {

	List<Integer> position(People people);
	
	People queryById(int id);
}
