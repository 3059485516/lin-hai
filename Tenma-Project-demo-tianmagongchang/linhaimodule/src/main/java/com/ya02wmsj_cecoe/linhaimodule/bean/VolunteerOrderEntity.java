package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-27.
 */
public class VolunteerOrderEntity implements Serializable {

    /**
     * id : 29
     * pid : 14
     * name : 【服务】就业帮扶、职业技能培训
     * type : 服务
     * level : 3
     */

    private String id;
    private String pid;
    private String name;
    private String type;
    private String level;
    private List<VolunteerOrderEntity> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<VolunteerOrderEntity> getChildren() {
        return children;
    }

    public void setChildren(List<VolunteerOrderEntity> children) {
        this.children = children;
    }
}
