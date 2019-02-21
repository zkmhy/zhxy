package com.zhxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoreController {	

	@RequestMapping("switching.to")
	public String switching() {
		return "switching";
	}

	@RequestMapping("version.to")
	public String version() {
		return "version";
	}

	@RequestMapping("addVersion.to")
	public String addVersion() {
		return "addversion";
	}
	@RequestMapping("charts.to")
	public String charts() {
		return "charts";
	}
	@RequestMapping("versioninfo.to")
	public String versionInfo() {
		return "versioninfo";
	}
	@RequestMapping("curr.to")
	public String curr() {
		return "curr";
	}
}
