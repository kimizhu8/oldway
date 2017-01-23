package net.iretailer.rest.model;

import java.util.Date;

public class Device {
    private Short id;

    private Short fkSiteId;

    private String name;

    private Boolean disabled;

    private String displayName;

    private Date lastUpdate;

    private String description;

    private String coordinate;

    private String macAddress;

    private String ipAddress;

    private String httpPort;

    private String httpsPort;

    private String hostname;

    private String hwPlatform;

    private String timezone;

    private String dst;

    private String deviceType;

    private String serialNumber;
    
    private Integer devicezoneCount;

    public Integer getDevicezoneCount() {
		return devicezoneCount;
	}

	public void setDevicezoneCount(Integer devicezoneCount) {
		this.devicezoneCount = devicezoneCount;
	}

	public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getFkSiteId() {
        return fkSiteId;
    }

    public void setFkSiteId(Short fkSiteId) {
        this.fkSiteId = fkSiteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort == null ? null : httpPort.trim();
    }

    public String getHttpsPort() {
        return httpsPort;
    }

    public void setHttpsPort(String httpsPort) {
        this.httpsPort = httpsPort == null ? null : httpsPort.trim();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public String getHwPlatform() {
        return hwPlatform;
    }

    public void setHwPlatform(String hwPlatform) {
        this.hwPlatform = hwPlatform == null ? null : hwPlatform.trim();
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone == null ? null : timezone.trim();
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst == null ? null : dst.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }
}