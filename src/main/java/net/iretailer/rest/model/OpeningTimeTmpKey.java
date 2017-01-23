package net.iretailer.rest.model;

import java.util.Date;

public class OpeningTimeTmpKey {
    private Short fkSiteId;

    private Date date;

    public Short getFkSiteId() {
        return fkSiteId;
    }

    public void setFkSiteId(Short fkSiteId) {
        this.fkSiteId = fkSiteId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}