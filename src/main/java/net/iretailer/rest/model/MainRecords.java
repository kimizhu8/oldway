package net.iretailer.rest.model;

import java.util.Date;

public class MainRecords {
	private Integer id;
	private Date time;
	private Short devicezoneId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Short getDevicezoneId() {
		return devicezoneId;
	}
	public void setDevicezoneId(Short devicezoneId) {
		this.devicezoneId = devicezoneId;
	}
}
