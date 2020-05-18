package com.nuo.netbar.common;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	public static Integer getYearDiff(Date date1,Long date2) {
		//此处需要的只是一个大概的年限，所以直接用年份减更加直观，可能数据不精准，但是只是从业务方面进行考虑的
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int year1 = cal.get(Calendar.YEAR);
		cal.setTime(new Date(date2));
		int year2 = cal.get(Calendar.YEAR);
		
		int diff = year1-year2;
		
		return diff>0?diff:-diff;
	}
	public static String getMonthTotal(Long date1,Long date2) {
		Long diff = date1-date2;
		Long month = diff/(1000L*60*60*24*30);
		Long year  = month/12;
		month = month%12;
		
		if(year>0) {
			if(month>0) {
				return year+"年"+month+"个月";
			}else {
				return year+"年";
			}
		}else {
			return month+"个月";
		}
	}
	public static String getListingDate(Date dateOfProduct) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOfProduct);
		int month = cal.get(Calendar.MONTH)+1;
		//Calendar工具里面的MONTH从0开始
		if(month<10) {
			return cal.get(Calendar.YEAR)+"-0"+month;
		}else {
			return cal.get(Calendar.YEAR)+"-"+month;
		}
	}
}
