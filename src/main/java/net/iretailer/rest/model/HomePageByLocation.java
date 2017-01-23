package net.iretailer.rest.model;

public class HomePageByLocation {
	private Integer countIn;
	private Integer countOut;
	private Integer passby;
	private String time;
	private String locationName;
	private Double StayTime;
	private Double sales;
	private Integer trades;
	private Integer goods;
	private Integer acreage;
	private Integer employeeNumber;
	public Integer getAcreage() {
		return acreage;
	}
	public void setAcreage(Integer acreage) {
		this.acreage = acreage;
	}
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Integer getTrades() {
		return trades;
	}
	public void setTrades(Integer trades) {
		this.trades = trades;
	}
	public Integer getGoods() {
		return goods;
	}
	public void setGoods(Integer goods) {
		this.goods = goods;
	}
	public Double getStayTime() {
		return StayTime;
	}
	public void setStayTime(Double stayTime) {
		StayTime = stayTime;
	}
	public Integer getCountIn() {
		return countIn;
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
	public Integer getPassby() {
		return passby;
	}
	public void setPassby(Integer passby) {
		this.passby = passby;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
}
