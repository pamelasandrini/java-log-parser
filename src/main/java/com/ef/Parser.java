package com.ef;

import java.io.IOException;
import java.security.InvalidParameterException;

import org.apache.commons.lang3.StringUtils;

import com.ef.bo.ParserCriteriaBO;
import com.ef.processor.ParserProcessor;

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

	private static ParserCriteriaBO criteria = new ParserCriteriaBO();
	private static String accessLog;

	public static void main(String[] args) throws IOException {

		System.out.println("Starting parser.");

		getCommandArguments(args);

		if (!criteria.isValidArgument()) {
			throw new InvalidParameterException("missing parameter from command line.");
		}

		new ParserProcessor(accessLog, criteria).processParse();

		System.out.println("Parser finished.");

	}

	private static void getCommandArguments(String[] args) {
		for (String string : args) {

			if (string.contains(ACCESS_LOG_ARG)) {

				accessLog = extractValue(string);
			}

			if (string.contains(START_DATE_ARG)) {

				criteria.setStartDate(extractValue(string));
			}

			if (string.contains(DURATION_ARG)) {

				criteria.setDuration(extractValue(string));
			}

			if (string.contains(THRESHOLD_ARG)) {

				criteria.setThreshold(extractValue(string));
			}
		}
	}

	private static String extractValue(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}

		return string.substring(string.indexOf('=') + 1, string.length());
	}
}
