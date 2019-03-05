package com.zhxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhxy.domain.Clazz;
import com.zhxy.domain.MyNotice;
import com.zhxy.service.ClazzService;
import com.zhxy.service.StudentService;

@ResponseBody
@Controller
@RequestMapping("ajax/page")
public class PageController {
	
	@Autowired
	StudentService studentService;
	@Autowired
	ClazzService clazzService;
	
	@RequestMapping("/mynotice")
	public PageInfo<MyNotice> notices(int id,int page){
		return clazzService.queryNotices(id, page);
	}
	
	@RequestMapping("/allClazz")
	public PageInfo<Clazz> clazzs(int page,int size){
		return clazzService.queryAll(page, size);
	}
}
