package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class ZhiyuanhuiListEntity implements Serializable {
    private List<ZhiyuanhuiEntity> list;

    public List<ZhiyuanhuiEntity> getList() {
        return list;
    }

    public void setList(List<ZhiyuanhuiEntity> list) {
        this.list = list;
    }
}
