package com.ef.util;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	/**
	 * Calculate the finalDate from startDate until the duration.
	 * 
	 * @return
	 */
	public static String getfinalDate(String startDate, String duration) {

		LocalDateTime date = LocalDateTime.parse(startDate.replace('.', 'T'));

		switch (duration) {
		case "hourly":
			date = date.plus(60, ChronoUnit.MINUTES);
			date = date.minus(1, ChronoUnit.SECONDS);
			break;

		case "daily":
			date = date.plus(24, ChronoUnit.HOURS);
			date = date.minus(1, ChronoUnit.SECONDS);
			break;

		default:
			throw new InvalidParameterException("invalid duration");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return date.format(formatter);
	}
}
