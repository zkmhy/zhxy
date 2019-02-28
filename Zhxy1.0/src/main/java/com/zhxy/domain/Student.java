package com.zhxy.domain;

public class Student {

	private int id;
	
	private String name;
	
	private int sex;
	
	private int age;
	
	private String sexInfo;
	
	private Education edu;
	
	private boolean checked;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Education getEdu() {
		return edu;
	}

	public void setEdu(Education edu) {
		this.edu = edu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
		this.sexInfo=sex>0?"男":"女";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSexInfo() {
		return sexInfo;
	}

	public void setSexInfo(String sexInfo) {
		this.sexInfo = sexInfo;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
