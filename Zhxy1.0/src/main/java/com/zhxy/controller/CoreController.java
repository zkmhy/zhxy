package com.zhxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoreController {	

	@RequestMapping("switching.to")
	public String switching() {
		return "switching";
	}
}
