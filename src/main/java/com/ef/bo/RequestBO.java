package com.ef.bo;

/**
 * BO class representing a request in log file.
 * 
 * @author pborsoni
 *
 */
public class RequestBO {

	private String ip;
	private String date;
	private String request;
	private String status;
	private String userAgent;

	public RequestBO() {
		super();
	}

	public RequestBO(String date, String ip, String request, String status, String userAgent) {
		this.ip = ip;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
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
