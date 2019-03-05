package com.zhxy.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhxy.domain.Clazz;
import com.zhxy.domain.ClazzInfo;
import com.zhxy.domain.MyNotice;

public interface ClazzService {

	/**
	 * id集合 为当前职位及以下的所有职员ID
	 * @param id
	 * @return
	 */
	
	List<Clazz> clazz(List<Integer> id);
	
	List<Clazz> clazz(List<Integer> id,int gradeid,String str);
	
	PageInfo<Clazz> queryAll(int page,int size);
	
	ClazzInfo auto(int gid,Integer mid,String date);
	
	String clazzName(int gid,Integer mid);
	
	boolean existClazz();
	
	Clazz queryById(int id);
	
	PageInfo<MyNotice> queryNotices(int id,int page);
	
	void appendClazz(ClazzInfo clazzInfo);
}
