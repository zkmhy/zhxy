package com.accp.service;

import java.util.Date;
import java.util.List;

import com.accp.domain.Plan;
import com.accp.domain.Plans;

public interface PlanService {

	List<Plans> weekPlan(Date date,int floor);
}
