package com.zhxy.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

@Controller
public class CoreController {

	@RequestMapping("/core")
	public String name() {
		return "core";
	}
}
