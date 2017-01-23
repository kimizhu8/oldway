package net.iretailer.rest.model;

public class RSitezoneDevicezone {
    private Integer id;

    private Short fkSiteZoneId;

    private Short fkDeviceZoneId;

    private Boolean reversed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getFkSiteZoneId() {
        return fkSiteZoneId;
    }

    public void setFkSiteZoneId(Short fkSiteZoneId) {
        this.fkSiteZoneId = fkSiteZoneId;
    }

    public Short getFkDeviceZoneId() {
        return fkDeviceZoneId;
    }

    public void setFkDeviceZoneId(Short fkDeviceZoneId) {
        this.fkDeviceZoneId = fkDeviceZoneId;
    }

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }
}