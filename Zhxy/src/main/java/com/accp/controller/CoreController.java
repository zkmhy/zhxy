package com.accp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accp.service.PlanService;
import com.accp.service.RoomService;

@Controller
public class CoreController {

	@Autowired
	PlanService planService;
	@Autowired
	RoomService roomService;
	
	@RequestMapping("/core")
	public String core(Model model) {
		model.addAttribute("plans", planService.weekPlan(new Date(),2));
		model.addAttribute("roomNum", roomService.roonum(2));
		return "core";
	}
}
