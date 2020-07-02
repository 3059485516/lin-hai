package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class OrginazeListEntity implements Serializable {

    /**
     * hours : 130.2
     * city_name : 杭州市
     * person : 12
     * name : 组织一
     * id : 210283410234243ajjdfasdfadfd
     * county_name : 下城区
     * province_name : 浙江省
     */
    private double hours;
    private String city_name;
    private int person;
    private String name;
    private String _id;
    private String county_name;
    private String province_name;
    private String avatar;

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this._id = id;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public double getHours() {
        return hours;
    }

    public String getCity_name() {
        return city_name;
    }

    public int getPerson() {
        return person;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return _id;
    }

    public String getCounty_name() {
        return county_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
