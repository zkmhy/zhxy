package com.zhxy.service;

import java.util.List;

import com.zhxy.domain.Section;
import com.zhxy.domain.Version;

public interface VersionService {

	List<Version> queryAll();
	
	List<Version> queryRest();
	
	Version queryById(int id);
	
	boolean hasSec(List<Section> list);
	
	boolean hasCurr(Integer[] list);
	
	void updateVersion(int vid,Integer[] list);
	
	void deleteSec(int vid,Integer[] list);
	
	void deleteCurr(int vid,Integer[] list);
	
}
