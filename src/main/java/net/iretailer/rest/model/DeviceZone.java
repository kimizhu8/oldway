package net.iretailer.rest.model;

public class DeviceZone {
    private Short id;

    private Short fkDeviceId;

    private String name;

    private Byte type;

    private Boolean disabled;

    private String displayName;

    private String description;

    private String coordinate;

    private Integer relatedTimes;
    
    private String deviceName;
    
    public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getRelatedTimes() {
		return relatedTimes;
	}

	public void setRelatedTimes(Integer relatedTimes) {
		this.relatedTimes = relatedTimes;
	}

	public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getFkDeviceId() {
        return fkDeviceId;
    }

    public void setFkDeviceId(Short fkDeviceId) {
        this.fkDeviceId = fkDeviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
}