package com.accp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accp.domain.Clazz;
import com.accp.domain.DatePlan;
import com.accp.domain.Event;
import com.accp.domain.Plan;
import com.accp.domain.Room;
import com.accp.mapper.ClazzMapper;
import com.accp.mapper.EventMapper;
import com.accp.mapper.PlanMapper;
import com.accp.mapper.RoomMapper;
import com.accp.service.PlanService;
import com.accp.utils.MyUtils;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

	private static final Log logger = LogFactory.getLog(PlanServiceImpl.class);
	@Autowired
	PlanMapper planMapper;
	@Autowired
	RoomMapper roomMapper;
	@Autowired
	ClazzMapper clazzMapper;
	@Autowired
	EventMapper eventMapper;

	@Override
	public List<DatePlan> weekPlan(Date date) {
		// TODO Auto-generated method stub
		List<DatePlan> list = new ArrayList<DatePlan>();
		List<Date> dates = MyUtils.weeks(date);
		for (Date itemDate : dates) {
			list.add(planMapper.datePlan(itemDate));
		}
		return list;
	}

	@Override
	public Plan existPlan(Date date, Room room, boolean ap) {
		// TODO Auto-generated method stub
		return planMapper.existPlan(date, room, ap);
	}

	@Override
	public void autoPlan(Date date) {
		// TODO Auto-generated method stub
		logger.info("开始排课");
		Random random = new Random();
		List<Date> dates = MyUtils.weeks(date);
		List<Clazz> clazzs = clazzMapper.allClazz();
		for (Clazz clazz : clazzs) {
			List<Room> inclass = roomMapper.allRoom(Room.INCLASS, clazz); // 上课教室
			List<Room> forstudy = roomMapper.allRoom(Room.INCLASS, clazz); // 自习教室
			Room onclassRoom = randomRoom(clazz, Room.INCLASS, dates);// 随机一个上课教室
			Room studyRoom = randomRoom(clazz, Room.FORSTUDY, dates);// 随机一个自习教室			
			Room tempRoom = inclass.get(random.nextInt(inclass.size())); // 临时 上课教室
			Room tempStudyRoom = forstudy.get(random.nextInt(inclass.size())); // 临时 自习教室
			while (tempRoom.getId() == onclassRoom.getId()) {
				tempRoom = inclass.get(random.nextInt(inclass.size()));
			}
			while (tempStudyRoom.getId() == studyRoom.getId()) {
				tempStudyRoom = forstudy.get(random.nextInt(inclass.size()));
			}
			for (Date tempDate : dates) {
				Event event = eventMapper.dateEvent(tempDate, clazz); // 查询是否有预约事件
				if (event != null) {
					if (event.getAp() == Event.All_DAY) { // 判断预约事件是否为一整天
						addPlan(new Plan(clazz, event.getRoom(), tempDate, Plan.AM, false, true));
						addPlan(new Plan(clazz, event.getRoom(), tempDate, Plan.PM, false, true));
					} else {
						addPlan(new Plan(clazz, event.getRoom(), tempDate, event.isaP(), false, true));
						addPlan(new Plan(clazz, event.getRoom(), tempDate, !event.isaP(), true, false));
					}
				} else {
					boolean classBoolean = random.nextBoolean();
					// 查询教室是否已被在时间段已被占用
					Plan plan = planMapper.existPlan(tempDate, onclassRoom, classBoolean);
					Room dateRoom = onclassRoom;
					if (plan != null) {
						dateRoom = tempRoom;
						plan = planMapper.existPlan(tempDate, tempRoom, classBoolean);
						if (plan != null) {
							List<Room> remainingRooms = roomMapper.remainingRoom(tempDate, clazz, classBoolean,
									Room.INCLASS);
							if (remainingRooms.size() > 0) { // 查询是否还有可用教室
								tempRoom = remainingRooms.get(random.nextInt(remainingRooms.size()));
								dateRoom = tempRoom;
								addPlan(new Plan(clazz, tempRoom, tempDate, classBoolean, true, false));
							} else {
								logger.info("没有可安排上课的教室,转为自习");
								plan = planMapper.existPlan(tempDate, studyRoom, classBoolean);
								if(plan!=null) {
									plan = planMapper.existPlan(tempDate, tempStudyRoom, classBoolean);
									if (plan != null) {
										List<Room> remainingRoom = roomMapper.remainingRoom(tempDate, clazz, classBoolean,
												Room.FORSTUDY);
										if (remainingRoom.size() > 0) { // 查询是否还有可用教室
											Room room = remainingRooms.get(random.nextInt(remainingRoom.size()));
											addPlan(
													new Plan(clazz, room, tempDate, classBoolean, false, false));
										} else {
											logger.info("没有可安排自习的教室,转为休息");
										}
									} else {
										addPlan(new Plan(clazz, tempStudyRoom, tempDate, classBoolean, false, false));
									}
								}else {
									addPlan(new Plan(clazz,studyRoom,tempDate,classBoolean,false,false));
								}
							}
						} else {
							addPlan(new Plan(clazz, tempRoom, tempDate, classBoolean, true, false));
						}
					} else {
						addPlan(new Plan(clazz, onclassRoom, tempDate, classBoolean, true, false));
					}
					boolean studyBoolean = !classBoolean;
					plan = planMapper.existPlan(tempDate, studyRoom, studyBoolean);
					if (plan != null) {
						plan = planMapper.existPlan(tempDate, dateRoom, studyBoolean);
						if (plan != null) {
							plan = planMapper.existPlan(tempDate, tempStudyRoom, studyBoolean);
							if (plan != null) {
								List<Room> remainingRooms = roomMapper.remainingRoom(tempDate, clazz, studyBoolean,
										Room.FORSTUDY);
								if (remainingRooms.size() > 0) { // 查询是否还有可用教室
									tempStudyRoom = remainingRooms.get(random.nextInt(remainingRooms.size()));
									addPlan(
											new Plan(clazz, tempStudyRoom, tempDate, studyBoolean, false, false));
								} else {
									logger.info("没有可安排自习的教室,转为休息");
								}
							} else {
								addPlan(new Plan(clazz, tempStudyRoom, tempDate, studyBoolean, false, false));
							}
						} else {
							addPlan(new Plan(clazz, dateRoom, tempDate, studyBoolean, false, false));
						}
					} else {
						addPlan(new Plan(clazz, studyRoom, tempDate, studyBoolean, false, false));
					}
				}
			}

		}
	}

	@Override
	public int addPlan(Plan plan) {
		// TODO Auto-generated method stub
		return planMapper.addPlan(plan);
	}
	
	public Room randomRoom(Clazz clazz,int type,List<Date> dates) {
		Random random = new Random();
		List<Room> lists=roomMapper.allRoom(type, clazz);
		Room room = lists.get(random.nextInt(lists.size()));// 随机一个自习教室
		if (planMapper.timePlan(dates.get(0), dates.get(dates.size() - 1), room).size() >= 6) {
			for (Room tempRoom : lists) {
				if (planMapper.timePlan(dates.get(0), dates.get(dates.size() - 1), room).size() < 6) {
					room = tempRoom;
					break;
				}
			}
		}
		return room;
	}
}
