package com.zhxy.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhxy.utils.MyUtils;

public class Calendar {

	private String title;

	private Map<String, List<DatePlan>> months;

	private List<DatePlan> weeks;

	private DatePlan datePlan;

	private Date date;

	private String dateStr;

	private String preDate;

	private String nextDate;

	private String today;

	private Date maxDate;

	private String max;

	private int type;

	private int month;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Map<String, List<DatePlan>> getMonths() {
		return months;
	}

	public void setMonths(Map<String, List<DatePlan>> months) {
		MyUtils.findAnother(months, month);
		this.months = months;
	}

	public List<DatePlan> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<DatePlan> weeks) {
		this.weeks = weeks;
	}

	public DatePlan getDatePlan() {
		return datePlan;
	}

	public void setDatePlan(DatePlan datePlan) {
		this.datePlan = datePlan;
	}

	public Calendar() {
		// TODO Auto-generated constructor stub
		this.today = MyUtils.format(new Date());
	}

	public Calendar(int type, Date date) {
		this();
		this.date = date;		
		this.type = type;
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		this.month = calendar.get(java.util.Calendar.MONTH);
		this.title = MyUtils.calendarTitle(type, date);
		this.preDate = MyUtils.preDate(type, date);
		this.nextDate = MyUtils.nextDate(type, date);
		this.dateStr = MyUtils.format(date);
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getPreDate() {
		return preDate;
	}

	public void setPreDate(String preDate) {
		this.preDate = preDate;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		maxDate = new Date().getTime() > maxDate.getTime() ? new Date() : maxDate;
		this.maxDate = maxDate;
		this.max = MyUtils.format(maxDate);
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}
}
