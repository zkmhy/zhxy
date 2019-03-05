package com.zhxy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.zhxy.domain.Calendar;
import com.zhxy.domain.Clazz;
import com.zhxy.domain.ClazzInfo;
import com.zhxy.domain.Curriculum;
import com.zhxy.domain.DatePlan;
import com.zhxy.domain.Event;
import com.zhxy.domain.Grade;
import com.zhxy.domain.InsertEvent;
import com.zhxy.domain.Major;
import com.zhxy.domain.People;
import com.zhxy.domain.Room;
import com.zhxy.domain.Section;
import com.zhxy.domain.Student;
import com.zhxy.domain.Version;
import com.zhxy.service.ClazzService;
import com.zhxy.service.CurrService;
import com.zhxy.service.EventService;
import com.zhxy.service.GradeService;
import com.zhxy.service.MajorService;
import com.zhxy.service.PeopleService;
import com.zhxy.service.PlanService;
import com.zhxy.service.RoomService;
import com.zhxy.service.StudentService;
import com.zhxy.service.VersionService;
import com.zhxy.utils.MyUtils;

@Controller
@RestController
@RequestMapping("/ajax/")
@SuppressWarnings("all")
public class AjaxController {

	@Autowired
	PlanService planService;
	@Autowired
	PeopleService peopleService;
	@Autowired
	GradeService gradeService;
	@Autowired
	EventService eventService;
	@Autowired
	RoomService roomService;
	@Autowired
	VersionService versionService;
	@Autowired
	CurrService currService;
	@Autowired
	MajorService majorService;
	@Autowired
	ClazzService clazzService;
	@Autowired
	StudentService studentService;
	@Autowired
	HttpSession session;

	@RequestMapping("calendar")
	public Calendar calendar(String str, int type) {
		Date date = MyUtils.isEmpty(str) ? new Date() : MyUtils.parse(str);
		return planService.calendar(type, date);
	}

	@RequestMapping("auto")
	public Calendar auto(int type) {
		planService.autoPlan();
		return planService.calendar(type, planService.maxDate());
	}

	@RequestMapping("cancelAdv")
	public Calendar calcelAdv(int type, String str) {
		planService.deleteAdv();
		Date date = MyUtils.isEmpty(str) ? new Date() : MyUtils.parse(str);
		return planService.calendar(type, date);
	}

	@RequestMapping("grade")
	public List<Grade> grade(String date) {
		People people = peopleService.queryById(2);
		return gradeService.grade(people, date);
	}

	@RequestMapping(value = "addEvent", produces = "application/json;charset=utf-8")
	public void event(@RequestBody InsertEvent event) {
		eventService.addEvent(event);
	}

	@RequestMapping("allEvent")
	public List<Event> allEvent() {
		return eventService.all();
	}

	@RequestMapping("switching")
	public Map<String, List<DatePlan>> switching() {
		People people = peopleService.queryById(3);
		return planService.switching(people);
	}

	@RequestMapping("spare")
	public List<Room> spare(String date, int id, boolean study, boolean ap, String map) {
		List<Room> lists = roomService.spare(date, id, study, ap);
		Map<String, Map<Integer, Integer>> maps = (Map<String, Map<Integer, Integer>>) session.getAttribute("score");
		if (maps != null) {
			Map<Integer, Integer> roomMap = maps.get(date);
			if (roomMap != null) {
				for (Integer integer : roomMap.keySet()) {
					if (!MyUtils.contains(lists, integer) && !roomMap.containsKey(roomMap.get(integer))) {
						Room room = roomService.queryById(integer);
						lists.add(room);
					}
					MyUtils.removeKey(lists, roomMap.get(integer));
				}
			}
		}
		return lists;
	}

	@RequestMapping("swRoom")
	public void swroom(int rid, int changeid, String date) {
		Map<String, Map<Integer, Integer>> maps = (Map<String, Map<Integer, Integer>>) session.getAttribute("score");
		if (maps == null) {
			maps = new HashMap<String, Map<Integer, Integer>>();
		}
		Map<Integer, Integer> rooms = maps.get(date);
		if (rooms == null) {
			rooms = new HashMap<>();
		}
		rooms.put(rid, changeid);
		maps.put(date, rooms);
		session.setAttribute("score", maps);
	}

	@RequestMapping("allversion")
	public List<Version> allVersions() {
		return versionService.queryAll();
	}

	@RequestMapping("restversion")
	public List<Version> restVersions() {
		return versionService.queryRest();
	}

	@RequestMapping("version")
	public Version queryByVersionId(int id) {
		return versionService.queryById(id);
	}

	@RequestMapping("restCurr")
	public List<Curriculum> sections(Integer vid, Integer mid, Integer gid) {
		return currService.restCurr(vid, mid, gid);
	}

	@RequestMapping("allCurr")
	public List<Curriculum> allCurr(Integer vid) {
		return currService.curriculums(vid);
	}

	@RequestMapping("queryMajor")
	public List<Major> queryMajors() {
		return majorService.majors(true);
	}

	@RequestMapping("allMajor")
	public List<Major> allMajors() {
		return majorService.majors(false);
	}

	@RequestMapping("allgrade")
	public List<Grade> allGrade(){
		return gradeService.allGrade();
	}
	
	@RequestMapping("queryGrade")
	public List<Grade> queryGrades(int mid) {
		return gradeService.queryGrades(mid);
	}

	@RequestMapping("queryCurr")
	public List<Curriculum> queryCurriculums(int mid, int gid) {
		return currService.queryCurriculums(mid, gid);
	}

	@RequestMapping("currs")
	public List<Curriculum> curriculums(Integer mid, Integer gid) {
		return currService.curriculums(mid, gid);
	}

	@RequestMapping("currinfo")
	public Curriculum curriculum(Integer id) {
		return currService.curriculum(id);
	}

	@RequestMapping(value = "addSection", produces = "application/json;charset=utf-8")
	public void addSection(@RequestBody List<Section> list) {
		currService.addSection(list);
	}

	@RequestMapping(value = "deleteSec", produces = "application/json;charset=utf-8")
	public void deleteSec(@RequestBody Curriculum curriculum) {
		currService.deleteSec(curriculum);
	}

	@RequestMapping(value = "hasSec", produces = "application/json;charset=utf-8")
	public boolean hasSec(@RequestBody List<Section> list) {
		return versionService.hasSec(list);
	}

	@RequestMapping(value = "hasCurr")
	public boolean hasCurr(Integer[] list) {
		return versionService.hasCurr(list);
	}

	@RequestMapping("deleteCurr")
	public void deleteCurr(Integer[] list) {
		currService.deleteCurr(list);
	}

	@RequestMapping("gradelist")
	public List<Grade> gradeList() {
		return gradeService.gradeList();
	}

	@RequestMapping(value = "addCurr", produces = "application/json;charset=utf-8")
	public void addCurr(@RequestBody Curriculum curriculum) {
		currService.addCurr(curriculum);
	}

	@RequestMapping(value = "updateCurr", produces = "application/json;charset=utf-8")
	public void updateCurr(@RequestBody Curriculum curriculum) {
		currService.updateCurr(curriculum);
	}

	@RequestMapping("updateVersion")
	public void updateVersion(int vid, Integer[] list) {
		versionService.updateVersion(vid, list);
	}

	@RequestMapping(value = "verdeletecurr")
	public void verDelCurr(int vid, Integer[] list) {
		versionService.deleteCurr(vid, list);
	}

	@RequestMapping(value = "verdeletesec")
	public void verDelSec(int vid, Integer[] list) {
		versionService.deleteSec(vid, list);
	}

	@RequestMapping("versionName")
	public String versionName() {
		return versionService.versionName();
	}

	@RequestMapping("allCheck")
	public boolean allCheck(Integer vid) {
		return versionService.allCheck(vid);
	}

	@RequestMapping(value = "addVer", produces = "application/json;charset=utf-8")
	public void addVer(@RequestBody Version version) {
		versionService.insertVer(version);
		;
	}

	@RequestMapping("autoClazz")
	public ClazzInfo autoClazz(Integer gid, Integer mid, String date) {
		ClazzInfo clazz=(ClazzInfo) session.getAttribute("autoClazz");
		clazz=clazzService.auto(gid, mid, date);
		session.setAttribute("autoClazz", clazz);
		return clazz;
	}
	
	@RequestMapping("queryAuto")
	public ClazzInfo queryAuto() {
		ClazzInfo clazz=(ClazzInfo) session.getAttribute("autoClazz");
		return clazz;
	}
	
	@RequestMapping("listPage")
	public List<Student> listPage(int page,Integer mid){
		ClazzInfo clazz=(ClazzInfo) session.getAttribute("autoClazz");
		if(clazz!=null) {
			return studentService.students(page,clazz.getAllStudents());
		}
		return null;
	}

	@RequestMapping("clazzName")
	public String clazzName(int gid, Integer mid) {
		return clazzService.clazzName(gid, mid);
	}

	@RequestMapping("ban")
	public List<People> queryBan() {
		return peopleService.bans();
	}
	
	@RequestMapping(value="stulistadd",produces="application/json;charset=utf-8")
	public void stulistadd(@RequestBody List<Student> lists) {
		ClazzInfo clazzInfo=(ClazzInfo)session.getAttribute("autoClazz");
		if(clazzInfo==null) {
			return;
		}
		List<Student> list=clazzInfo.getAllStudents();
		list.addAll(lists);
		clazzInfo.setAllStudents(list);
		session.setAttribute("autoClazz", clazzInfo);
	}

	@RequestMapping(value="stulistdel",produces="application/json;charset=utf-8")
	public void stulistdel(@RequestBody List<Student> lists) {
		ClazzInfo clazzInfo=(ClazzInfo)session.getAttribute("autoClazz");
		if(clazzInfo==null) {
			return;
		}
		for (Student student : lists) {
			for (int i = 0; i < clazzInfo.getAllStudents().size(); i++) {
				if(clazzInfo.getAllStudents().get(i).getId()==student.getId()) {
					clazzInfo.getAllStudents().remove(i);
					i=clazzInfo.getAllStudents().size();
				}
			}
		}
		clazzInfo.setAllStudents(clazzInfo.getAllStudents());
		session.setAttribute("autoClazz", clazzInfo);
	}

	@RequestMapping("freeStu")
	public PageInfo<Student> freeStu(int page,Integer mid){
		ClazzInfo clazz=(ClazzInfo) session.getAttribute("autoClazz");
		if(clazz==null) {
			return null;
		}
		return studentService.frees(page,mid, clazz.getAllStudents());
	}
	
	@RequestMapping("queryStu")
	public PageInfo<Student> queryStu(int page,Integer mid){
		ClazzInfo clazz=(ClazzInfo) session.getAttribute("autoClazz");
		if(clazz==null) {
			return null;
		}
		return studentService.querys(page,mid, clazz.getAllStudents());
	}
	
	@RequestMapping("versionCurriculum")
	public List<Curriculum> versionCurriculum(int vid,int gid,Integer mid){
		return currService.versionCurr(vid, gid,mid);
	}
	
	@RequestMapping("teachers")
	public List<People> teachers(int cid){
		return peopleService.teachers(cid);
	}
	
	@RequestMapping("teacherCurr")
	public List<People> teacher(int id,Integer[] list){		
		return peopleService.teachers(id,list);
	}

	@RequestMapping("clazzInfo")
	public Clazz queryClazz(int id) {
		return clazzService.queryById(id);
	}
	
	@RequestMapping("clazzPlan")
	public List<Clazz> clazzPlan(int id){
		return planService.weekPlan(id);
	}
	
	@RequestMapping(value="appendClazz",produces="application/json;charset=utf-8")
	public void appendClazz(@RequestBody ClazzInfo clazzInfo) {
		clazzService.appendClazz(clazzInfo);	
	}
}

