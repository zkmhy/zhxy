package com.zhxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxy.domain.Curriculum;
import com.zhxy.domain.Section;
import com.zhxy.mapper.CurriculumMapper;
import com.zhxy.service.CurrService;
import com.zhxy.service.PeopleService;
import com.zhxy.service.SectionService;
import com.zhxy.service.VersionService;

@Service
@Transactional
public class CurrServiceImpl implements CurrService{

	@Autowired
	CurriculumMapper curriculumMapper;
	@Autowired
	SectionService sectionService;
	@Autowired
	VersionService versionService;
	@Autowired
	PeopleService peopleService;
	
	@Override
	public List<Curriculum> queryCurriculums(int mid, int gid) {
		// TODO Auto-generated method stub
		return curriculumMapper.curriculums(mid, gid);
	}

	@Override
	public List<Curriculum> curriculums(Integer mid, Integer gid) {
		// TODO Auto-generated method stub
		return curriculumMapper.currInfos(mid, gid);
	}

	@Override
	public Curriculum curriculum(Integer id) {
		// TODO Auto-generated method stub
		return curriculumMapper.queryById(id);
	}

	@Override
	public void addSection(List<Section> list) {
		// TODO Auto-generated method stub
		if(list!=null && list.size()>0) {
			sectionService.addSection(list);
			curriculumMapper.addSection(list);
			Integer[] id=new Integer[] {list.get(0).getCid()};			
			curriculumMapper.updateCurr(id);
		}
	}

	@Override
	public void deleteSec(Curriculum curriculum) {
		// TODO Auto-generated method stub
		if(curriculum.getLists()!=null&& curriculum.getLists().size()>0) {
			curriculumMapper.deleteSec(curriculum);
			sectionService.deleteSec(curriculum.getLists());
			Integer[] id=new Integer[] {curriculum.getId()};
			curriculumMapper.updateCurr(id);
		}
	}

	@Override
	public void deleteCurr(Integer[] list) {
		// TODO Auto-generated method stub
		if(list!=null && list.length>0) {
			curriculumMapper.deleteCurr(list);
			curriculumMapper.deleteSection(list);
			curriculumMapper.deleteSectionInfo(list);
		}
	}

	@Override
	public void addCurr(Curriculum curriculum) {
		// TODO Auto-generated method stub
		curriculumMapper.addCurr(curriculum);
		if(curriculum.getLists()!=null && curriculum.getLists().size()>0) {
			addSection(curriculum.getLists());
		}
		if(curriculum.getTid()!=null && curriculum.getTid().length>0) {
			peopleService.teachers(curriculum.getId(), curriculum.getTid());
		}
	}

	@Override
	public void updateCurr(Curriculum curriculum) {
		// TODO Auto-generated method stub
		Integer[] list=new Integer[] {curriculum.getId()};
		curriculumMapper.updateCurriculum(curriculum);
		sectionService.updateSec(curriculum.getLists());
		curriculumMapper.updateCurr(list);
	}

	@Override
	public List<Curriculum> restCurr(Integer vid,Integer mid,Integer gid) {
		// TODO Auto-generated method stub
		return curriculumMapper.restCurr(vid,mid,gid);
	}

	@Override
	public List<Curriculum> curriculums(Integer vid) {
		// TODO Auto-generated method stub
		return curriculumMapper.allCurr(vid);
	}

	@Override
	public List<Curriculum> versionCurr(int vid, int gid,Integer mid) {
		// TODO Auto-generated method stub		
		return curriculumMapper.versionCurriculums(vid, gid,mid);
	}

	@Override
	public boolean existCurr(int gid,Integer mid) {
		// TODO Auto-generated method stub
		int vid=versionService.nowId();
		return curriculumMapper.existCurr(vid,gid,mid);
	}

	@Override
	public void delCurr(int cid) {
		// TODO Auto-generated method stub
		curriculumMapper.delCurr(cid);
	}
	
}
