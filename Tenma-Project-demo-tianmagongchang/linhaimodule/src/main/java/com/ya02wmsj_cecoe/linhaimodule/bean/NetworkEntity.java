package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-17.
 */
public class NetworkEntity implements Serializable {
    private String title;
    private List<MainNodeEntity> sublist;

    public NetworkEntity() {

    }

    public NetworkEntity(String title, List<MainNodeEntity> list) {
        this.title = title;
        this.sublist = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MainNodeEntity> getSublist() {
        return sublist;
    }

    public void setSublist(List<MainNodeEntity> sublist) {
        this.sublist = sublist;
    }
}
