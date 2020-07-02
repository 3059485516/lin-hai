package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class ZjkProfessionEntity implements Serializable {

    /**
     * createtime : 1546825785000
     * name : 林木
     * id : 3
     * cateList : null
     * parentid : 1
     */
    private long createtime;
    private String name;
    private String id;
    private List<ZjkProfessionEntity> cateList;
    private String parentid;
    private boolean selected;

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCateList(List<ZjkProfessionEntity> cateList) {
        this.cateList = cateList;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public long getCreatetime() {
        return createtime;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<ZjkProfessionEntity> getCateList() {
        return cateList;
    }

    public String getParentid() {
        return parentid;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
