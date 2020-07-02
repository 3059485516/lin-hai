package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-25.
 */
public class OrganizeDetailEntity implements Serializable {

    /**
     * id : 43
     * pid : 0
     * name : “最多跑一次”宣讲志愿服务队
     * desc :
     * phone :
     * address :
     * category_id : 17
     * level : 0
     * points : 0
     * unit :
     * charge_name :
     */

    private String id;
    private String pid;
    private String name;
    private String desc;
    private String phone;
    private String address;
    private String category_id;
    private String level;
    private String points;
    private String unit;
    private String charge_name;
    private List<ServiceListEntity> service_list;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCharge_name() {
        return charge_name;
    }

    public void setCharge_name(String charge_name) {
        this.charge_name = charge_name;
    }

    public List<ServiceListEntity> getService_list() {
        return service_list;
    }

    public void setService_list(List<ServiceListEntity> service_list) {
        this.service_list = service_list;
    }
}
