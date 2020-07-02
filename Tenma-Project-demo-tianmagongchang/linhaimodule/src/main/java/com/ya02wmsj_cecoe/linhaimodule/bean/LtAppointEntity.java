package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LtAppointEntity implements Serializable {

    /**
     * reason : 22222
     * ca_name : 大洋社区文化礼堂
     * phone : 13121322121
     * s_time : 2020-03-31 16:20:38
     * ctime : 2020-03-20 16:20:35
     * review_msg :
     * id : 3
     * e_time : 2020-03-31 16:20:38
     * use_unit : 黄佳琪
     * linkman : 黄佳琪
     * source_name : 图书馆
     * status : 待审核
     */
    private String reason;
    private String ca_name;
    private String phone;
    private String s_time;
    private String ctime;
    private String review_msg;
    private String id;
    private String e_time;
    private String use_unit;
    private String linkman;
    private String source_name;
    private String status;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCa_name(String ca_name) {
        this.ca_name = ca_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setReview_msg(String review_msg) {
        this.review_msg = review_msg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    public void setUse_unit(String use_unit) {
        this.use_unit = use_unit;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public String getCa_name() {
        return ca_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getS_time() {
        return s_time;
    }

    public String getCtime() {
        return ctime;
    }

    public String getReview_msg() {
        return review_msg;
    }

    public String getId() {
        return id;
    }

    public String getE_time() {
        return e_time;
    }

    public String getUse_unit() {
        return use_unit;
    }

    public String getLinkman() {
        return linkman;
    }

    public String getSource_name() {
        return source_name;
    }

    public String getStatus() {
        return status;
    }
}
