package net.iretailer.rest.dto;

public class SalesDataDTO {
	private Integer id;
	private String time;
	private Short devicezoneId;
	private float countSales;
	private Integer countTrades;
	private Integer countGoods;
	private String devicezoneName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Short getDevicezoneId() {
		return devicezoneId;
	}
	public void setDevicezoneId(Short devicezoneId) {
		this.devicezoneId = devicezoneId;
	}
	public float getCountSales() {
		return countSales;
	}
	public void setCountSales(float countSales) {
		this.countSales = countSales;
	}
	public Integer getCountTrades() {
		return countTrades;
	}
	public void setCountTrades(Integer countTrades) {
		this.countTrades = countTrades;
	}
	public Integer getCountGoods() {
		return countGoods;
	}
	public void setCountGoods(Integer countGoods) {
		this.countGoods = countGoods;
	}
	public String getDevicezoneName() {
		return devicezoneName;
	}
	public void setDevicezoneName(String devicezoneName) {
		this.devicezoneName = devicezoneName;
	}
	
}
