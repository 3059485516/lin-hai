package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class LtEvaEntity implements Serializable {

    /**
     * level : 0
     * pid : 0
     * id : 8
     * title : 评审节点1
     * is_once : 0
     * demand : 评审描述1
     * point : 10
     */
    private int level;
    private String pid;
    private String id;
    private String title;
    private String is_once;
    private String demand;
    private String point;
    private List<LtEvaEntity> children;

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIs_once(String is_once) {
        this.is_once = is_once;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getLevel() {
        return level;
    }

    public String getPid() {
        return pid;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIs_once() {
        return is_once;
    }

    public String getDemand() {
        return demand;
    }

    public String getPoint() {
        return point;
    }

    public List<LtEvaEntity> getChildren() {
        return children;
    }

    public void setChildren(List<LtEvaEntity> children) {
        this.children = children;
    }
}
