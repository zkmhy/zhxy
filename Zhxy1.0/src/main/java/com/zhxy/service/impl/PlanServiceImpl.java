package com.zhxy.service.impl;

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

import com.zhxy.domain.Clazz;
import com.zhxy.domain.DatePlan;
import com.zhxy.domain.Event;
import com.zhxy.domain.People;
import com.zhxy.domain.Plan;
import com.zhxy.domain.Room;
import com.zhxy.mapper.ClazzMapper;
import com.zhxy.mapper.EventMapper;
import com.zhxy.mapper.PeopleMapper;
import com.zhxy.mapper.PlanMapper;
import com.zhxy.mapper.RoomMapper;
import com.zhxy.service.PeopleService;
import com.zhxy.service.PlanService;
import com.zhxy.utils.MyUtils;

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
	@Autowired
	PeopleService peopleService;

	@Override
	public Plan existPlan(Date date, Room room, boolean ap) {
		// TODO Auto-generated method stub
		return planMapper.existPlan(date, room, ap);
	}

	public void auto(Date date) {
		Random random = new Random();
		List<People> teachers = peopleMapper.allTeacher();
		List<Date> weeks = MyUtils.weeks(date);
		Calendar calendar = Calendar.getInstance();
		for (People teacher : teachers) {
			logger.info(teacher.getName());
			for (Date day : weeks) {
				if (day.getTime() < new Date().getTime() || MyUtils.isToday(day)) {
					continue;
				}
				logger.info(MyUtils.format(day));
				List<Date> lists = MyUtils.weeks(date);
				Date begin = lists.get(0);
				Date end = lists.get(lists.size() - 1);
				calendar.setTime(day);
				calendar.add(Calendar.DATE, -1);
				Date dayBefore = calendar.getTime();
				List<Clazz> clazzs = clazzMapper.findClazz(teacher, begin, end, dayBefore);
				List<Clazz> dayClazzs = new ArrayList<>();
				int i = 0;
				Clazz tempClazz = null;
				for (Clazz clazz : clazzs) {
					logger.info(clazz.getName());
					if (!hasEvent(clazz, day)) {
						if (i < 2) {
							dayClazzs.add(clazz);
							i++;
						} else {
							tempClazz = clazz;
						}
					}
				}
				boolean isStudy = isStudyByOnself(tempClazz, begin, end);
				if (!isStudy && tempClazz != null) {
					Room room = findRoom(false, tempClazz, new People(), day);
					addPlan(new Plan(tempClazz, room, day, false, false, false));
					addPlan(new Plan(tempClazz, room, day, true, false, false));
				}
				for (Clazz clazz : dayClazzs) {
					logger.info(".................");
					logger.info(clazz.getName());
					isStudy = isStudyByOnself(clazz, begin, end);
					boolean ap = random.nextBoolean();
					Room studyRoom = findRoom(true, clazz, teacher, day);
					if (clazz.getWeekNum() >= 4 && !isStudy) {
						logger.info("一天自习");
						addPlan(new Plan(clazz, studyRoom, day, ap, false, false));
						addPlan(new Plan(clazz, studyRoom, day, !ap, false, false));
						continue;
					}
					if (studyRoom != null) {
						ap = studyRoom.getAp() == null ? ap : studyRoom.getAp();
						if (clazz.getWeekNum() < 4) {
							if (peopleMapper.isPlanBusy(teacher, day, ap) == null
									&& peopleMapper.isEventBusy(teacher, day, ap) == null) {
								logger.info("上课");
								addPlan(new Plan(clazz, studyRoom, day, ap, true, false));
							} else {
								addPlan(new Plan(clazz, studyRoom, day, ap, false, false));
							}
							ap = !ap;
							studyRoom = findRoom(false, clazz, teacher, day, ap);
							if (studyRoom != null) {
								addPlan(new Plan(clazz, studyRoom, day, ap, false, false));
							}
						}
					}
				}
			}
		}
	}

	@Override
	public Date autoPlan() {
		// TODO Auto-generated method stub
		logger.info("<<<<<<<<<<<<<<<<<<< · 开始排课");
		Date date = planMapper.maxDate();
		Date today = new Date();
		date = date.getTime() > today.getTime() ? MyUtils.nextWeekMonday(date) : today;
		auto(date);
		return date;
	}

	public Room findRoom(boolean study, Clazz clazz, People teacher, Date day) {
		Random random = new Random();
		Room studyRoom = roomMapper.teacherRoom(day, teacher, clazz, study);
		if (studyRoom == null || (clazz.getCurr().getId() == -1 && !studyRoom.getFortest())) {
			List<Room> studyRooms = roomMapper.classRoom(clazz, study, day);
			if (studyRooms.size() > 0) {
				/**
				 * 预科 只能去机房 不是预科 则可以去任意教室 -1 为预科
				 */
				if (clazz.getCurr().getId() != -1 || !study) {
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
					/**
					 * 预科 只能去机房 不是预科 则可以去任意教室 -1 为预科
					 */
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

	public Room findRoom(boolean study, Clazz clazz, People teacher, Date day, boolean ap) {
		Room room = findRoom(study, clazz, teacher, day);
		if (room != null && null != room.getAp() && ap != room.getAp()) {
			room = findRoom(study, clazz, new People(), day);
		}
		return room;
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
				if (event.isStudy()) {
					addPlan(new Plan(clazz, event.getRoom(), date, event.isaP(), false, true));
					addPlan(new Plan(clazz, event.getRoom(), date, !event.isaP(), false, false));
				} else {
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
		Map<String, List<DatePlan>> maps = new HashMap<String, List<DatePlan>>();
		Map<String, List<Date>> months = MyUtils.calendar(date);
		for (String string : months.keySet()) {
			List<DatePlan> datePlans = new ArrayList<>();
			for (Date temp : months.get(string)) {
				datePlans.add(datePlan(temp));
			}
			maps.put(string, datePlans);
		}
		return maps;
	}

	@Override
	public com.zhxy.domain.Calendar calendar(int type, Date date) {
		// TODO Auto-generated method stub
		com.zhxy.domain.Calendar calendar = new com.zhxy.domain.Calendar(type, date);
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
		calendar.setMaxDate(maxDate());
		return calendar;
	}

	@Override
	public DatePlan advPlan(Date date, People people) {
		// TODO Auto-generated method stub
		List<Integer> ids = peopleService.position(people);
		List<Clazz> clazzs = clazzMapper.teacherClazz(ids);
		List<Clazz> list = new ArrayList<>();
		for (Clazz clazz : clazzs) {
			Clazz temp = new Clazz(clazz);
			temp.setPlans(planMapper.classadvPlan(temp.getId(), date));
			list.add(temp);
		}
		DatePlan datePlan = new DatePlan();
		datePlan.setDate(date);
		datePlan.setPlans(list);
		return datePlan;
	}

	@Override
	public DatePlan datePlan(Date date) {
		// TODO Auto-generated method stub
		return planMapper.datePlan(date);
	}

	public List<DatePlan> week(Date date) {
		// TODO Auto-generated method stub
		List<Date> weeks = MyUtils.week(date);
		List<DatePlan> datePlans = new ArrayList<>();
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
	public boolean isStudyByOnself(Clazz clazz, Date begin, Date end) {
		// TODO Auto-generated method stub
		Integer study = clazzMapper.isStudyByOnself(clazz, begin, end);
		return study != null && study > 1;
	}

	@Override
	public boolean existAuto() {
		// TODO Auto-generated method stub
		return planMapper.existAuto() > 0;
	}

	@Override
	public void pushAuto() {
		// TODO Auto-generated method stub
		planMapper.pushAuto();
	}

	@Override
	public Date maxDate() {
		// TODO Auto-generated method stub
		return planMapper.maxDate();
	}

	@Override
	public Map<String, List<DatePlan>> switching(People people) {
		// TODO Auto-generated method stub
		Date date = maxDate();
		Date minDate = planMapper.mindate();
		if (date == null) {
			return null;
		}
		if (date.getTime() < new Date().getTime() || MyUtils.isToday(date)) {
			return null;
		}
		Map<String, List<Date>> plans = MyUtils.fill(date, minDate);
		Map<String, List<DatePlan>> maps = new HashMap<>();
		for (String keyString : plans.keySet()) {
			List<DatePlan> datePlans = new ArrayList<>();
			for (Date tempDate : plans.get(keyString)) {
				if(tempDate!=null) {
					datePlans.add(advPlan(tempDate, people));					
				}
			}
			maps.put(keyString, datePlans);
		}
		return maps;
	}

	@Override
	public List<Clazz> weekPlan(int id) {
		// TODO Auto-generated method stub
		List<Clazz> list=new ArrayList<>();
		List<Date> dates=MyUtils.week(new Date());
		for (Date date : dates) {
			Clazz clazz=new Clazz();
			clazz.setPlans(planMapper.clazzdatePlan(id, date));
			list.add(clazz);
		}
		return list;
	}

}
