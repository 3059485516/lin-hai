package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishApplyInfo implements Serializable {

    /**
     * name : 陈小华
     * phone : 18267839966
     * address : 西溪花园流芳苑10幢
     * user_id : c8660342c12c407db87c61e137f86cb2
     * ctime : 2019-08-14 14:44:22
     * audit_time : 2019-08-15 15:03:17
     * region_code
     * region_name
     */

    private String name;
    private String phone;
    private String address;
    private String user_id;
    private String ctime;
    private String audit_time;
    private String region_code;
    private String region_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
