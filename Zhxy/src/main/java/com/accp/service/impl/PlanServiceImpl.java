package com.accp.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accp.domain.Clazz;
import com.accp.domain.DatePlan;
import com.accp.domain.Event;
import com.accp.domain.People;
import com.accp.domain.Plan;
import com.accp.domain.Room;
import com.accp.mapper.ClazzMapper;
import com.accp.mapper.EventMapper;
import com.accp.mapper.PeopleMapper;
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
	@Autowired
	PeopleMapper peopleMapper;

	@Override
	public List<DatePlan> weekPlan(Date date) {
		// TODO Auto-generated method stub
		List<DatePlan> list = new ArrayList<DatePlan>();
		List<Date> dates = MyUtils.weeks(date);
		for (Date itemDate : dates) {
			list.add(datePlan(itemDate));
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
		logger.info("<<<<<<<<<<<<<<<<<<< · 开始排课");
		Random random = new Random();
		List<People> teachers = peopleMapper.allTeacher();
		List<Date> weeks = MyUtils.weeks(date);
		Calendar calendar = Calendar.getInstance();
		for (People teacher : teachers) {
			for (Date day : weeks) {
				List<Date> lists=MyUtils.weeks(date);
				Date begin=lists.get(0);
				Date end=lists.get(lists.size()-1);
				calendar.setTime(day);
				calendar.add(Calendar.DATE, -1);
				Date dayBefore = calendar.getTime();
				calendar.add(Calendar.DATE, -7);
				Date beforeWeek = calendar.getTime();
				List<Clazz> clazzs = clazzMapper.findClazz(teacher, beforeWeek, dayBefore);
				List<Clazz> dayClazzs = new ArrayList<>();
				int i = 0;
				for (Clazz clazz : clazzs) {
					if (!hasEvent(clazz, day)) {
						if (i < 2) {
							dayClazzs.add(clazz);
							i++;
						}
					}
				}
				for (Clazz clazz : dayClazzs) {
					boolean isStudy=isStudyByOnself(clazz, begin, end);
					boolean ap=random.nextBoolean();
					Room studyRoom=findRoom(true, clazz, teacher, day);
					if (studyRoom != null) {
						ap= studyRoom.getAp() == null ? ap : studyRoom.getAp();
						if(clazz.getPlanNum()<4) {
							boolean toStudy=random.nextBoolean();
							if(toStudy&&!isStudy) {
								addPlan(new Plan(clazz, studyRoom, day, ap, false, false));
								addPlan(new Plan(clazz, studyRoom, day, !ap, false, false));
								continue;
							}
							if(peopleMapper.isPlanBusy(teacher, day, ap)==null && peopleMapper.isEventBusy(teacher, day, ap)==null) {
								addPlan(new Plan(clazz, studyRoom, day, ap, true, false));
							}else {
								addPlan(new Plan(clazz, studyRoom, day, ap, false, false));							
							}							
						}else {
							if(isStudy) {
								addPlan(new Plan(clazz, studyRoom, day, ap, false, false));
								addPlan(new Plan(clazz, studyRoom, day, !ap, false, false));
								continue;								
							}
						}
					}
					ap=!ap;
					studyRoom=findRoom(false, clazz, teacher, day);
					if(studyRoom!=null) {
						ap= studyRoom.getAp() == null ? ap : studyRoom.getAp();
						addPlan(new Plan(clazz, studyRoom, day, ap, false, false));					
					}
				}
			}
		}
	}

	public Room findRoom(boolean study, Clazz clazz, People teacher, Date day) {
		Random random = new Random();
		Room studyRoom = roomMapper.teacherRoom(day, teacher, clazz, study);
		if (studyRoom == null || (clazz.getCurr().getId() == -1 && !studyRoom.getFortest())) {
			List<Room> studyRooms = roomMapper.classRoom(clazz, study, day);
			if (studyRooms.size() > 0) {
				if (clazz.getCurr().getId() != -1) {
					studyRoom = studyRooms.get(random.nextInt(studyRooms.size()));
				} else {
					for (Room stuRoom : studyRooms) {
						if (stuRoom.getInclass() && stuRoom.getFortest()) {
							studyRoom = stuRoom;
							break;
						}
					}
				}
			} else {
				studyRooms = roomMapper.remainRoom(day, clazz, study);
				if (studyRooms.size() > 0) {
					if (clazz.getCurr().getId() != -1) {
						studyRoom = studyRooms.get(random.nextInt(studyRooms.size()));
					} else {
						for (Room stuRoom : studyRooms) {
							if (stuRoom.getInclass() && stuRoom.getFortest()) {
								studyRoom = stuRoom;
								break;
							}
						}
					}
				}
			}
		}
		return studyRoom;
	}

	@Override
	public int addPlan(Plan plan) {
		// TODO Auto-generated method stub
		plan.setAdvance(true);
		return planMapper.addPlan(plan);
	}

	public Boolean hasEvent(Clazz clazz, Date date) {
		Event event = eventMapper.dateEvent(date, clazz.getId());
		if (event != null) {
			if (event.getAp() == Event.All_DAY) { // 判断预约事件是否为一整天
				addPlan(new Plan(clazz, event.getRoom(), date, Plan.AM, false, true));
				addPlan(new Plan(clazz, event.getRoom(), date, Plan.PM, false, true));
			} else {
				if(event.isStudy()) {
					addPlan(new Plan(clazz, event.getRoom(), date, event.isaP(), false, true));
					addPlan(new Plan(clazz, event.getRoom(), date, !event.isaP(), false, false));					
				}else {
					addPlan(new Plan(clazz, event.getRoom(), date, event.isaP(), false, true));					
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int deleteAdv() {
		// TODO Auto-generated method stub
		return planMapper.deleteAdv();
	}

	@Override
	public Map<String, List<DatePlan>> months(Date date) {
		// TODO Auto-generated method stub
		Map<String, List<DatePlan>> maps=new HashMap<String, List<DatePlan>>();
		Map<String, List<Date>> months=MyUtils.calendar(date);
		for (String string : months.keySet()) {
			List<DatePlan> datePlans=new ArrayList<>();
			for (Date temp : months.get(string)) {
				datePlans.add(datePlan(temp));
			}
			maps.put(string, datePlans);
		}
		return maps;
	}

	@Override
	public com.accp.domain.Calendar calendar(int type, Date date) {
		// TODO Auto-generated method stub
		com.accp.domain.Calendar calendar=new com.accp.domain.Calendar(type,date);
		switch (type) {
		case 1:
			calendar.setMonths(months(date));
			break;
		case 2:
			calendar.setWeeks(week(date));
			break;
		case 3:
			calendar.setDatePlan(datePlan(date));
			break;
		}
		return calendar;
	}

	@Override
	public DatePlan datePlan(Date date) {
		// TODO Auto-generated method stub
		return planMapper.datePlan(date);
	}

	@Override
	public List<DatePlan> week(Date date) {
		// TODO Auto-generated method stub
		List<Date> weeks=MyUtils.week(date);
		List<DatePlan> datePlans=new ArrayList<>();
		for (Date tempDate : weeks) {
			datePlans.add(datePlan(tempDate));
		}
		return datePlans;
	}

	@Override
	public void updateDate(Date date, int id) {
		// TODO Auto-generated method stub
		planMapper.updateDate(date, id);
	}

	@Override
	public boolean isStudyByOnself(Clazz clazz,Date begin,Date end) {
		// TODO Auto-generated method stub
		Integer study=clazzMapper.isStudyByOnself(clazz,begin,end);
		return study!=null && study>1;
	}
	
	
}
