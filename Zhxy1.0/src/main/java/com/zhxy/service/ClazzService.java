package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.ClazzInfo;

public interface ClazzService {

	/**
	 * id集合 为当前职位及以下的所有职员ID
	 * @param id
	 * @return
	 */
	
	List<Clazz> clazz(List<Integer> id);
	
	List<Clazz> clazz(List<Integer> id,int gradeid,String str);
	
	List<Clazz> queryAll();
	
	ClazzInfo auto(int gid,Integer mid,String date);
	
	String clazzName(int gid,Integer mid);
	
	boolean existClazz();
}
