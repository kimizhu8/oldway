package net.iretailer.rest.model;

import java.util.Date;

public class Subject {
    private Short id;

    private Short siteId;

    private Date startTime;

    private Date endTime;

    private String name;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getSiteId() {
        return siteId;
    }

    public void setSiteId(Short siteId) {
        this.siteId = siteId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}