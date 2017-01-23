package net.iretailer.rest.model;

public class RecordsInout {
    private Integer fkRecordsId;

    private Short countIn;

    private Short countOut;

    public Integer getFkRecordsId() {
        return fkRecordsId;
    }

    public void setFkRecordsId(Integer fkRecordsId) {
        this.fkRecordsId = fkRecordsId;
    }

    public Short getCountIn() {
        return countIn;
    }

    public void setCountIn(Short countIn) {
        this.countIn = countIn;
    }

    public Short getCountOut() {
        return countOut;
    }

    public void setCountOut(Short countOut) {
        this.countOut = countOut;
    }
}