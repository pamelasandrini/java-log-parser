package com.ef;

import java.io.File;

import com.ef.util.FileUtil;
import com.google.common.base.Strings;

/**
 * Main class for executing Parser program.
 * 
 * @author pborsoni
 *
 */
public class Parser {

	private static final String ACCESS_LOG_ARG = "accesslog";
	private static final String START_DATE_ARG = "startDate";
	private static final String DURATION_ARG = "duration";
	private static final String THRESHOLD_ARG = "threshold";

	private static String accessLog;
	private static String startDate;
	private static String duration;
	private static String threshold;

	private static File logFile;

	public static void main(String[] args) {

		getArguments(args);

		// if log file was passed then gets it from the path, otherwise gets it
		// from the resource folder as default

		if (!Strings.isNullOrEmpty(accessLog) && FileUtil.fileExists(accessLog)) {

			logFile = FileUtil.getLogFileFromPath(accessLog);

		} else {

			System.out.println("Could not determine access.log from command line. Using default access.log file");
			logFile = FileUtil.getLogFileFromResourceFolder();

		}
		
		// parse the log
		
		// save in DB
		
		// check the IP requests

	}

	private static void getArguments(String[] args) {
		for (String string : args) {

			if (string.contains(ACCESS_LOG_ARG)) {

				accessLog = extractValue(string);
			}

			if (string.contains(START_DATE_ARG)) {

				startDate = extractValue(string);
			}

			if (string.contains(DURATION_ARG)) {

				duration = extractValue(string);
			}

			if (string.contains(THRESHOLD_ARG)) {

				threshold = extractValue(string);
			}
		}
	}

	private static String extractValue(String string) {
		if (Strings.isNullOrEmpty(string)) {
			return null;
		}

		return string.substring(string.indexOf('=') + 1, string.length());
	}
}
