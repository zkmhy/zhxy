package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.People;

public interface ClazzService {

	List<Clazz> clazz(List<Integer> id);
	
	List<Clazz> clazz(List<Integer> id,int gradeid);
}
