package net.iretailer.rest.model;

public class HomePageByZonetype {
	private Integer countIn;
	private Integer countOut;
	private String zoneType;
	private String zoneName;
	private String time;
	private Integer passby;
	private Double StayTime;
	public Double getStayTime() {
		return StayTime;
	}
	public void setStayTime(Double stayTime) {
		StayTime = stayTime;
	}
	public Integer getPassby() {
		return passby;
	}
	public void setPassby(Integer passby) {
		this.passby = passby;
	}
	public Integer getCountIn() {
		return countIn;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setCountIn(Integer countIn) {
		this.countIn = countIn;
	}
	public Integer getCountOut() {
		return countOut;
	}
	public void setCountOut(Integer countOut) {
		this.countOut = countOut;
	}
	public String getZoneType() {
		return zoneType;
	}
	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

}
