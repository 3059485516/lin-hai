package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class VenueEntity implements Serializable {

    /**
     * id : 1
     * name : 临海体育馆
     * desc : 描述内容
     * address : 开发大道与临海大道交叉口
     * charge_name : 6
     * phone : 9
     * ctime : 2019-07-16 11:49:41
     */

    private String id;
    private String name;
    private String desc;
    private String address;
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
