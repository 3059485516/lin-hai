package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class ServiceConcatEntity implements Serializable {

    /**
     * phone : 15258963258
     * name : 小芳
     */
    private String phone;
    private String name;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}
