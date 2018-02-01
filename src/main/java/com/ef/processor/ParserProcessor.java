package com.ef.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ef.bo.ParserCriteriaBO;
import com.ef.bo.RequestBO;
import com.ef.dao.BlockedIPDao;
import com.ef.dao.RequestDao;
import com.ef.util.DateUtil;
import com.ef.util.FileUtil;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class ParserProcessor {

	private File logFile;
	private ParserCriteriaBO criteria;
	private String blockReason;
	private Set<String> ips = new LinkedHashSet<>();
	private StringBuilder partialLog = new StringBuilder();
	private static final Splitter MY_SPLITTER = Splitter.on('|').trimResults().omitEmptyStrings();
	private static final int DATE_SPLIT = 0;
	private static final int IP_SPLIT = 1;
	private static final int REQUEST_SPLIT = 2;
	private static final int STATUS_SPLIT = 3;
	private static final int USER_AGENT_SPLIT = 4;

	public ParserProcessor(String accessLog, ParserCriteriaBO criteria) {
		logFile = loadLogFile(accessLog);
		this.criteria = criteria;
		blockReason = String.format("Reached the threshold of %s requests.", criteria.getThreshold());
	}

	public void processParse() throws IOException {

		List<RequestBO> requestList = new ArrayList<>();
		Path path = logFile.toPath();

		// parse the log
		Files.lines(path).forEach(line -> {

			requestList.add(splitRequestFromLine(line));
		});

		// store all request lines
		RequestDao requestDao = new RequestDao();
		requestDao.insert(requestList);

		// store blocked ips
		Set<String> blockedIps = getBlockedIps(partialLog.toString(), ips);
		storeBlockedIps(blockedIps);

	}

	private void getIpsFromLineInDuration(String ip, String dateString, String line) {

		LocalDateTime date = DateUtil.convertStringToLocalDateTime(dateString);
		LocalDateTime startDate = DateUtil.convertStringToLocalDateTime(criteria.getStartDate());
		LocalDateTime finalDate = DateUtil.convertStringToLocalDateTime(criteria.getfinalDate());
		
		if (!(date.isBefore(startDate) || date.isAfter(finalDate))) {

			partialLog.append(line);
			ips.add(ip);
		}
	}

	private RequestBO splitRequestFromLine(String line) {

		List<String> itemsSplitted = MY_SPLITTER.splitToList(line);

		String date = itemsSplitted.get(DATE_SPLIT);
		String ip = itemsSplitted.get(IP_SPLIT);

		getIpsFromLineInDuration(ip, date, line);

		return new RequestBO(date, ip, removeQuotes(itemsSplitted.get(REQUEST_SPLIT)), itemsSplitted.get(STATUS_SPLIT),
				removeQuotes(itemsSplitted.get(USER_AGENT_SPLIT)));

	}

	private void storeBlockedIps(Set<String> blockedIps) {
		BlockedIPDao blockedIpDao = new BlockedIPDao();

		for (String ip : blockedIps) {

			blockedIpDao.insert(ip, blockReason);
		}
	}

	private Set<String> getBlockedIps(String partialLog, Set<String> ips) {

		Set<String> blockedIps = new HashSet<>();
		BlockedIPDao blockedIpDao = new BlockedIPDao();

		for (String ip : ips) {
			if (StringUtils.countMatches(ip, partialLog) >= criteria.getThreshold()) {
				blockedIps.add(ip);
				blockedIpDao.insert(ip, blockReason);
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
