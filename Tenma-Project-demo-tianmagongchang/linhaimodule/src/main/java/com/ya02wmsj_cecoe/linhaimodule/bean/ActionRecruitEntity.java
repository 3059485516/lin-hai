package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-23.
 */
public class ActionRecruitEntity implements Serializable {

    /**
     * id : 65
     * name : 【点单服务】“最多跑一次”政策宣讲 活动招募 （活动区域：大洋街道）
     * content :
     * start_time : 2019-07-12 00:00:00
     * end_time : 2019-07-27 00:00:00
     * man_id : 4d92a971360243b0bab91e2ffc6740e3
     * ctime : 2019-07-12 16:24:36
     * icon_path : /uploads/pic/20190712/1562919575_376188846.png
     * address :
     * sign_num : 0
     * service_name : “最多跑一次”政策宣讲
     * participate : 未报名
     * status : 进行中
     * cur_num : 0
     */

    private String id;
    private String name;
    private String content;
    private String start_time;
    private String end_time;
    private String man_id;
    private String ctime;
    private String icon_path;
    private String address;
    private String sign_num;
    private String service_name;
    private String participate;
    private String status;
    private String cur_num;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getMan_id() {
        return man_id;
    }

    public void setMan_id(String man_id) {
        this.man_id = man_id;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSign_num() {
        return sign_num;
    }

    public void setSign_num(String sign_num) {
        this.sign_num = sign_num;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getParticipate() {
        return participate;
    }

    public void setParticipate(String participate) {
        this.participate = participate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCur_num() {
        return cur_num;
    }

    public void setCur_num(String cur_num) {
        this.cur_num = cur_num;
    }
}
