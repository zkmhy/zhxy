package com.accp.service;

import java.util.Date;
import java.util.List;

import com.accp.domain.DatePlan;
import com.accp.domain.Plan;
import com.accp.domain.Room;

/**
 * 课程计划 Service 接口类
 * 提供了按日期查询日期所在周课程安排情况 的接口
 * @author 晨曦
 *
 */
public interface PlanService {

	List<DatePlan> weekPlan(Date date);
	
	Plan existPlan(Date date,Room room,boolean ap);
	
	/**
	 * 自动排课接口
	 * @param date
	 */
	void autoPlan(Date date);
	
	int addPlan(Plan plan);
}
