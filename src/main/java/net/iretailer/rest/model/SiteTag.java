package net.iretailer.rest.model;

public class SiteTag {
    private Short id;

    private Short fkSiteId;

    private String type;

    private String value;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}