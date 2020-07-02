package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class CategoryTypeEntity implements Serializable {

    /**
     * id : 6
     * name : 法律服务
     * pid : 0
     * level : 0
     */

    private String id;
    private String name;
    private String pid;
    private String level;
    private List<CategoryTypeEntity> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<CategoryTypeEntity> getList() {
        return list;
    }

    public void setList(List<CategoryTypeEntity> list) {
        this.list = list;
    }
}
