package com.ef.util;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	/**
	 * Calculate the finalDate from startDate until the duration.
	 * 
	 * @return
	 */
	public static String calculateFinalDate(String startDate, String duration) {

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

	public static Date convertStringToDate(String date) {

		Date newDate = null;

		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			newDate = new java.sql.Date(fmt.parse(date).getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static Timestamp convertStringToTimestamp(String date) {

		return new Timestamp(convertStringToDate(date).getTime());

	}

	public static LocalDateTime convertStringToLocalDateTime(String date) {

		return LocalDateTime.parse(date.replace(' ', 'T'));

	}

	public static String standardizeDateFormat(String date) {
		return date.replace('.', 'T');
	}

}
