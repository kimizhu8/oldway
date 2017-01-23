package net.iretailer.rest.model;

public class HomePage {
	private String time;
	private Integer enter;
	private Integer exit;
	private Integer passby;
	private Double sales;
	private Integer goods;
	private Integer trades;
	private String displayName;
	private Integer acreage;
	private Double StayTime;
	private Integer employeeNumber;
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public Double getStayTime() {
		return StayTime;
	}
	public void setStayTime(Double stayTime) {
		StayTime = stayTime;
	}
	public Integer getAcreage() {
		return acreage;
	}
	public void setAcreage(Integer acreage) {
		this.acreage = acreage;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getEnter() {
		return enter;
	}
	public void setEnter(Integer enter) {
		this.enter = enter;
	}
	public Integer getExit() {
		return exit;
	}
	public void setExit(Integer exit) {
		this.exit = exit;
	}
	public Integer getPassby() {
		return passby;
	}
	public void setPassby(Integer passby) {
		this.passby = passby;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Integer getGoods() {
		return goods;
	}
	public void setGoods(Integer goods) {
		this.goods = goods;
	}
	public Integer getTrades() {
		return trades;
	}
	public void setTrades(Integer trades) {
		this.trades = trades;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
