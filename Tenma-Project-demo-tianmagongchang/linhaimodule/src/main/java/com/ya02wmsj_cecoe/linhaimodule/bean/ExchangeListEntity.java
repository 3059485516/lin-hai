package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class ExchangeListEntity implements Serializable {
    private int total;
    private List<ExchangeEntitly> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ExchangeEntitly> getRows() {
        return rows;
    }

    public void setRows(List<ExchangeEntitly> rows) {
        this.rows = rows;
    }
}
