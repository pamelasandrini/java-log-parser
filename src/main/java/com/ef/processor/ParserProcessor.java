package com.ef.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ef.bo.ParserCriteriaBO;
import com.ef.bo.RequestBO;
import com.ef.util.FileUtil;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class ParserProcessor {

	private File logFile;
	private ParserCriteriaBO criteria;
	private static final Splitter MY_SPLITTER = Splitter.on('|').trimResults().omitEmptyStrings();

	public ParserProcessor(String accessLog, ParserCriteriaBO criteria) {
		logFile = loadLogFile(accessLog);
		this.criteria = criteria;
	}

	public void processParse() throws IOException {

		List<RequestBO> requestList = new ArrayList<>();
		StringBuilder partialLog = new StringBuilder();
		Set<String> ips = new LinkedHashSet<>();
		Path path = logFile.toPath();

		// parse the log
		Files.lines(path).forEach(line -> {

			List<String> items = MY_SPLITTER.splitToList(line);
			String date = items.get(0);
			String ip = items.get(1);
			RequestBO request = new RequestBO(date, ip, removeQuotes(items.get(2)), items.get(3),
					removeQuotes(items.get(4)));

			requestList.add(request);

			//TODO: if date >= criteria.getStartDate() && date <= criteria.getfinalDate()
			if (true) {
				// add this line to a different list
				partialLog.append(line);
				ips.add(ip);
			}
		});

		//TODO: save the request list in DB

		Set<String> blockedIps = checkBlockedIps(partialLog.toString(), ips);

		//TODO: save the blocked IPs in DB
	}

	private Set<String> checkBlockedIps(String partialLog, Set<String> ips) {

		Set<String> blockedIps = new HashSet<>();

		for (String ip : ips) {
			if (StringUtils.countMatches(ip, partialLog) >= criteria.getThreshold()) {
				blockedIps.add(ip);
			}
		}

		return blockedIps;
	}

	/**
	 * Removes quotes from start and end of <code>string</code>
	 * 
	 * @param string
	 * @return
	 */
	private String removeQuotes(String string) {

		return string.replaceAll("^\"|\"$", "");

	}

	private File loadLogFile(String accessLog) {
		if (!Strings.isNullOrEmpty(accessLog) && FileUtil.fileExists(accessLog)) {

			return FileUtil.getLogFileFromPath(accessLog);

		} else {

			System.out.println(
					"Could not determine access.log from command line. Using default access.log file from resource folder");
			return FileUtil.getLogFileFromResourceFolder();

		}
	}

}
