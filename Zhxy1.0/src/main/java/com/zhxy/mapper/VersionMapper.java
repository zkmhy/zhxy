package com.zhxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhxy.domain.Section;
import com.zhxy.domain.Version;

@Mapper
public interface VersionMapper {

	List<Version> queryAll();

	Version queryById(int id);

	int hasSec(List<Section> list);

	int hasCurr(Integer[] list);

	List<Version> queryRest();
	
	Integer[] restCurr(int vid);
	
	void update(int id); //更新 版本——课程 关联表 数据
	
	void updateHour(int id); //更新 版本表 数据
	
	void updateVersion(int vid,Integer[] list);
	
	void updateCurr(int vid,Integer[] list);
	
	void deleteSec(int vid,Integer[] list);
	
	void deleteCurr(int vid,Integer[] list);
	
	int versionName();
	
	boolean allCheck(Integer vid);
	
	void insert(Version version);
	
}
