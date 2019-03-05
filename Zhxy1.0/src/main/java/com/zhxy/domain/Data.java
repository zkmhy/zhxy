package com.zhxy.domain;

import com.zhxy.utils.MyUtils;

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
		this.width=MyUtils.floatNum(width, 2);
		if(this.width<0.1) {
			this.width=0.1;
		}
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
		this.width=(double)Math.round(width*100)/100;
	}

}
