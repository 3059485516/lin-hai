package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-27.
 */
public class OrderDetailEntity implements Serializable {

    /**
     * id : 15
     * pid : 1
     * name : 廉政文化、基层反腐宣讲
     * desc :
     * type : 服务
     * service_time :
     * service_scope :
     */

    private String id;
    private String pid;
    private String name;
    private String desc;
    private String type;
    private String service_time;
    private String service_scope;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public String getService_scope() {
        return service_scope;
    }

    public void setService_scope(String service_scope) {
        this.service_scope = service_scope;
    }
}
