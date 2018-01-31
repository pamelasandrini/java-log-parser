package com.ef.bo;

import org.apache.commons.lang3.StringUtils;

import com.ef.util.DateUtil;

/**
 * BO class that holds the arguments from the command line.
 * 
 * @author pborsoni
 *
 */
public class ParserCriteriaBO {

	private String startDate;
	private String duration;
	private String threshold;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getThreshold() {
		return Integer.parseInt(threshold);
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	/**
	 * @return true if all arguments were passed.
	 */
	public boolean isValidArgument() {

		return StringUtils.isNotBlank(duration) && StringUtils.isNotBlank(startDate)
				&& StringUtils.isNotBlank(threshold);
	}

	/**
	 * @return the finalDate as the log file format
	 */
	public String getfinalDate() {
		return DateUtil.getfinalDate(startDate, duration);
	}

	/**
	 * @return the startDate as the log file format
	 */
	public String getStartDateFormatted() {

		return startDate.replace('.', ' ');
	}
}
