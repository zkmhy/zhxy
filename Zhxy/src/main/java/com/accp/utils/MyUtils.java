package com.accp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {

	private static SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日");
	
	public static void formatTime() {
		sdf.format(new Date());
	}
}
