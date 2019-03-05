package com.zhxy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhxy.domain.Clazz;
import com.zhxy.domain.DatePlan;
import com.zhxy.domain.People;
import com.zhxy.domain.Plan;
import com.zhxy.domain.Room;

@Mapper
public interface PlanMapper {

	Plan queryById(int id);
	
	/**
	 * 根据日期查找当天所有课程安排
	 * @param date
	 * @return
	 */
	DatePlan datePlan(Date date);

	/**
	 * 添加课表安排
	 * @param plan
	 * @return 返回受影响行数
	 */
	int addPlan(Plan plan);
	
	/**
	 * 查询是否在某日期，某教室，某时刻 是否有安排
	 * @param date 日期
	 * @param room 教室
	 * @param ap 上/下午
	 * @return 返回 课表计划类
	 */
	Plan existPlan(@Param("date")Date date,@Param("room")Room room,@Param("ap")boolean ap);
	
	Plan clazzPlan(@Param("date")Date date,@Param("clazz")Clazz clazz,@Param("ap")boolean ap);

	Plan peoplePlan(@Param("date")Date date,@Param("people")People people,@Param("ap")boolean ap);
	
	/**m
	 * 查找某一时间段内 教室的课表安排
	 * @param date 开始时间
	 * @param end 结束时间
	 * @param room 教室
	 * @return 教室集合
	 */
	List<Plan> timePlan(@Param("begin")Date date,@Param("end")Date end,@Param("room")Room room);
	
	int deleteAdv();
	
	int isExistPlan(@Param("date")Date date,@Param("clazz")Clazz clazz);
	
	List<Date> dates(@Param("clazz")Clazz clazz);
	
	int classNumOfWeek(@Param("begin")Date begin,@Param("end")Date end,@Param("clazz")Clazz clazz);
	
	int updateDate(@Param("date")Date date,@Param("id")int id);
	
	int existAuto();
	
	void pushAuto();
	
	Date maxDate();
	
	Date mindate();
	
	Date maxdate();
	
	List<Plan> classadvPlan(@Param("id")int id,@Param("date")Date date);
	
	List<Plan> clazzdatePlan(@Param("id")int id,@Param("date")Date date);
}
