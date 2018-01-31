package com.ef.bo;

import java.util.Date;

/**
 * BO class representing a request in log file.
 * 
 * @author pborsoni
 *
 */
public class RequestBO {

	private String ip;
	private String stringDate;
	private String request;
	private String status;
	private String userAgent;
	private Date date;

	public RequestBO() {
		super();
	}

	public RequestBO(String stringDate, String ip, String request, String status, String userAgent) {
		this.ip = ip;
		this.stringDate = stringDate;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String date) {
		this.stringDate = date;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
