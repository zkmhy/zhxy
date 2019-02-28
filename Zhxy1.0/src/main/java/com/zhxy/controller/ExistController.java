package com.zhxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhxy.service.ClazzService;
import com.zhxy.service.GradeService;
import com.zhxy.service.PeopleService;
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
	
	@RequestMapping("ver")
	public boolean existVer() {
		return versionService.existVer();
	}
	
	@RequestMapping("ban")
	public boolean existBan() {
		return peopleService.existBan();
	}
	
	@RequestMapping("teacher")
	public boolean existTeacher() {
		return peopleService.existTeacher();		
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
}
