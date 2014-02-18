package de.fluchtwege.weatherhistory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	public static String getCurrentDateFormatted() {
		Date d = new Date(System.currentTimeMillis());
		return format.format(d);
	}

	public static String getDateFormatted(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return format.format(cal.getTime());
	}
}
