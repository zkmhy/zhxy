package com.zhxy.domain;

public class Data {

	private String name;
	
	private double num;
	
	private double width;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width=(double)Math.round(width*10000)/10000;
	}
	
	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}
	
	public Data() {
		
	}
	
	public Data(String name,double num,double width) {
		this.name=name;
		this.num=num;
		this.width=(double)Math.round(width*10000)/10000;
	}

}
