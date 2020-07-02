package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class MyExchangeListEntity implements Serializable {
    private int total;
    private List<MyExchangeEntity> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MyExchangeEntity> getRows() {
        return rows;
    }

    public void setRows(List<MyExchangeEntity> rows) {
        this.rows = rows;
    }
}
