package net.iretailer.rest.model;

public class Site {
    private Short id;

    private Short fkLocationId;

    private Short parentId;

    private Byte type;

    private Short rank;

    private Boolean disabled;

    private String name;

    private String displayName;

    private Integer operationAcreage;

    private String description;

    private String coordinate;

    private String address;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getFkLocationId() {
        return fkLocationId;
    }

    public void setFkLocationId(Short fkLocationId) {
        this.fkLocationId = fkLocationId;
    }

    public Short getParentId() {
        return parentId;
    }

    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Short getRank() {
        return rank;
    }

    public void setRank(Short rank) {
        this.rank = rank;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public Integer getOperationAcreage() {
        return operationAcreage;
    }

    public void setOperationAcreage(Integer operationAcreage) {
        this.operationAcreage = operationAcreage;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}