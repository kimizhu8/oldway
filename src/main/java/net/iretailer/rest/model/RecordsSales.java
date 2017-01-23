package net.iretailer.rest.model;

public class RecordsSales {
    private Integer fkRecordsId;

    private Float countSales;

    private Integer countTrades;

    private Integer countGoods;

    public Integer getFkRecordsId() {
        return fkRecordsId;
    }

    public void setFkRecordsId(Integer fkRecordsId) {
        this.fkRecordsId = fkRecordsId;
    }

    public Float getCountSales() {
        return countSales;
    }

    public void setCountSales(Float countSales) {
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
}