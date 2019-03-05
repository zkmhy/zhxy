package com.zhxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhxy.service.ClazzService;
import com.zhxy.service.CurrService;
import com.zhxy.service.GradeService;
import com.zhxy.service.PeopleService;
import com.zhxy.service.PlanService;
import com.zhxy.service.RoomService;
import com.zhxy.service.StudentService;
import com.zhxy.service.VersionService;

@Controller
@RestController
@RequestMapping("/ajax/exist/")
public class ExistController {

	@Autowired
	ClazzService clazzService;
	@Autowired
	PeopleService peopleService;
	@Autowired
	VersionService versionService;
	@Autowired
	RoomService roomService;
	@Autowired
	StudentService studentService;
	@Autowired
	GradeService gradeService;
	@Autowired
	CurrService currService;
	@Autowired
	PlanService planService;
	
	@RequestMapping("ver")
	public boolean existVer() {
		return versionService.existVer();
	}
	
	@RequestMapping("ban")
	public boolean existBan() {
		return peopleService.existBan();
	}
	
	@RequestMapping("room")
	public boolean existRoom() {
		return roomService.existRoom();
	}
	
	@RequestMapping("student")
	public boolean existStudent(Integer mid) {
		return studentService.existStudent(mid);
	}
	
	@RequestMapping("clazz")
	public boolean existClazz() {
		return clazzService.existClazz();
	}
	
	@RequestMapping("grade")
	public boolean existGrade() {
		return gradeService.existGrade();
	}
	@RequestMapping("stu")
	public boolean existStu(Integer mid) {
		return studentService.existStudent(mid);
	}
	
	@RequestMapping("teacher")
	public boolean existTea(int gid,Integer mid) {
		return peopleService.existTeacher(gid,mid);
	}
		
	@RequestMapping("curr")
	public boolean existCurr(int gid,Integer mid) {
		return currService.existCurr(gid, mid);
	}

	@RequestMapping("auto")
	public boolean existAuto() {
		return planService.existAuto();
	}
}
