package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishListEntity implements Serializable {

    /**
     * id : 1
     * title : 我想要一本中医药的书籍
     * desc : 我想学习中医的知识，希望得到大家的帮忙！
     * name : 陈利华
     * phone : 18267839955
     * address : 西溪花园流芳苑8幢
     * region_code : 331082107232
     * story :
     * status : 已实现
     * ctime : 2019-08-13 17:10:29
     * user_id : c8660342c12c407db87c61e137f86cb2
     * region_name : 临海市/括苍镇/独山村
     * icon_path
     */

    private String id;
    private String title;
    private String desc;
    private String name;
    private String phone;
    private String address;
    private String region_code;
    private String story;
    private String status;
    private String ctime;
    private String user_id;
    private String region_name;
    private String icon_path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }
}
