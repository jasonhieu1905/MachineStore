package com.machine.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class DateUtils {

	public static List<Date> getStartAndEndOfDate(Date date){
		List<Date> startAndEndDate = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startAndEndDate.add(cal.getTime());
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		startAndEndDate.add(cal.getTime());
		return startAndEndDate;
	}
	
	public static List<Date> getStartAndEndOfPeriodTime(Date start,Date end){
		List<Date> startAndEndDate = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startAndEndDate.add(cal.getTime());
		
		cal.setTime(end);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		startAndEndDate.add(cal.getTime());
		return startAndEndDate;
	}
	
}
