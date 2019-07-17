package com.etas.api.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityHelper {
	
	public static Date getDateFromString(String dateString) throws ParseException {
		if (dateString == "" || dateString == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
	}
	
	public static Date getDateTimeFromString(String dateTimeString) throws ParseException {
		if (dateTimeString == "" || dateTimeString == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateTimeString);
	}

}
