package com.zkf.datascreen.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {




	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;


	}


	/**
	 * 根据当前日期获得是星期几
	 * time=yyyy-MM-dd
	 * @return
	 */
	public static String getWeek(String time) {
		String Week = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int wek=c.get(Calendar.DAY_OF_WEEK);

		if (wek == 1) {
			Week += "SU";
		}
		if (wek == 2) {
			Week += "MO";
		}
		if (wek == 3) {
			Week += "TU";
		}
		if (wek == 4) {
			Week += "WE";
		}
		if (wek == 5) {
			Week += "TH";
		}
		if (wek == 6) {
			Week += "FR";
		}
		if (wek == 7) {
			Week += "SA";
		}
		return Week;
	}
}
