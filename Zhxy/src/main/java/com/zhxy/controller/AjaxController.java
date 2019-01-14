package com.zhxy.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhxy.domain.Calendar;
import com.zhxy.domain.Grade;
import com.zhxy.domain.People;
import com.zhxy.service.GradeService;
import com.zhxy.service.PeopleService;
import com.zhxy.service.PlanService;
import com.zhxy.utils.MyUtils;

@Controller
@RestController
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	PlanService planService;
	@Autowired
	PeopleService peopleSerivce;
	@Autowired
	GradeService gradeService;

	@RequestMapping("/calendar")
	public String calendar(String str, int type) {
		Date date=MyUtils.isEmpty(str)? new Date() : MyUtils.parse(str);
		Calendar calendar = planService.calendar(type, date);
		return JSON.toJSONString(calendar, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping("/auto")
	public String auto(int type) {
		Date date=MyUtils.nextWeekMonday(new Date());
		planService.autoPlan(date);
		Calendar calendar = planService.calendar(type, date);
		return JSON.toJSONString(calendar, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping("/existAuto")
	public String existAuto() {
		return planService.existAuto()+"";
	}
	
	@RequestMapping("/cancelAdv")
	public String calcelAdv(int type,String str) {
		planService.deleteAdv();
		Date date=MyUtils.isEmpty(str)? new Date() : MyUtils.parse(str);
		Calendar calendar = planService.calendar(type, date);
		return JSON.toJSONString(calendar, SerializerFeature.DisableCircularReferenceDetect);		
	}
	
	@RequestMapping("/updatePlan")
	public String updatePlan(String str,List<Integer> id) {
		Date date=MyUtils.parse(str);
		for (Integer integer : id) {
			planService.updateDate(date, integer);
		}
		return "";
	}
	
	@RequestMapping("/core")
	public List<Grade> grade() {		
		People people=peopleSerivce.queryById(1);
		return gradeService.grade(people);
	}
}
