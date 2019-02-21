package com.zhxy.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxy.domain.Section;
import com.zhxy.domain.Version;
import com.zhxy.mapper.SectionMapper;
import com.zhxy.mapper.VersionMapper;
import com.zhxy.service.VersionService;

@Service
@Transactional
public class VersionServiceImpl implements VersionService {

	@Autowired
	VersionMapper versionMapper;
	@Autowired
	SectionMapper sectionMapper;

	@Override
	public List<Version> queryAll() {
		// TODO Auto-generated method stub
		return versionMapper.queryAll();
	}

	@Override
	public Version queryById(int id) {
		// TODO Auto-generated method stub
		return versionMapper.queryById(id);
	}

	@Override
	public boolean hasSec(List<Section> list) {
		// TODO Auto-generated method stub
		if (list.size() == 0) {
			return false;
		}
		return versionMapper.hasSec(list) > 0;
	}

	@Override
	public boolean hasCurr(Integer[] list) {
		// TODO Auto-generated method stub
		if (list == null || list.length == 0) {
			return false;
		}
		return versionMapper.hasCurr(list) > 0;
	}

	@Override
	public List<Version> queryRest() {
		// TODO Auto-generated method stub
		return versionMapper.queryRest();
	}

	@Override
	public void updateVersion(int vid, Integer[] list) {
		// TODO Auto-generated method stub
		if (list != null && list.length > 0) {
			versionMapper.updateVersion(vid, list);
			Integer[] curr = versionMapper.restCurr(vid);
			if (curr.length > 0) {
				versionMapper.updateCurr(vid, curr);
			}
			updateVersion(vid);
		}
	}

	@Override
	public void deleteSec(int vid, Integer[] list) {
		// TODO Auto-generated method stub
		if (list != null && list.length > 0) {
			versionMapper.deleteSec(vid, list);
			updateVersion(vid);
		}
	}

	@Override
	public void deleteCurr(int vid, Integer[] list) {
		// TODO Auto-generated method stub
		if (list != null && list.length > 0) {
			versionMapper.deleteCurr(vid, list);
		}
	}

	@Override
	public void updateVersion(int id) {
		// TODO Auto-generated method stub
		versionMapper.update(id);
		versionMapper.updateHour(id);
	}

	@Override
	public String versionName() {
		// TODO Auto-generated method stub
		return "ACCP " + versionMapper.versionName() + ".0";
	}

	@Override
	public boolean allCheck(Integer vid) {
		// TODO Auto-generated method stub
		if (vid == null) {
			return false;
		}
		return versionMapper.allCheck(vid);
	}

	@Override
	public void insertVer(Version version) {
		// TODO Auto-generated method stub
		versionMapper.insert(version);
		updateVersion(version.getId(), version.getList());
	}

}
