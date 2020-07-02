package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class VenueDetailEntity implements Serializable {

    /**
     * id : 2
     * name : 冶水休闲体育公园
     * desc : 9
     * address : 江滨东路347号
     * latitude : 0.0000000
     * longitude : 0.0000000
     * charge_name : 5
     * phone : 8
     * ctime : 2019-07-16 11:53:39
     */

    private String id;
    private String name;
    private String desc;
    private String address;
    private String latitude;
    private String longitude;
    private String charge_name;
    private String phone;
    private String ctime;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCharge_name() {
        return charge_name;
    }

    public void setCharge_name(String charge_name) {
        this.charge_name = charge_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
