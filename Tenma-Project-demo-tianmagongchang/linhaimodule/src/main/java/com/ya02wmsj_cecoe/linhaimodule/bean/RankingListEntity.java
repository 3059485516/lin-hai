package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class RankingListEntity implements Serializable {
    private List<RankingEntity> list;

    public List<RankingEntity> getList() {
        return list;
    }

    public void setList(List<RankingEntity> list) {
        this.list = list;
    }
}
