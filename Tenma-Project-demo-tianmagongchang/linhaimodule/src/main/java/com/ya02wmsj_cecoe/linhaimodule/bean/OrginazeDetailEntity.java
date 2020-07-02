package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class OrginazeDetailEntity implements Serializable {
    private String departmentid;        //组织id
    private String title;       //组织名称
    private String avatar;  //组织头像
    private String hours;   //公益时长
    private String points;  //公益积分
    private String person;  //组织人数
    private String province_name;   //省
    private String city_name;   //市
    private String county_name; //区
    private String ranking; //排名
    private String is_signup;   //是否加入机构0-未加入 1-已加入 2-审核中
    private String is_main; //1-是主机构，0-不是主机构

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getIs_signup() {
        return is_signup;
    }

    public void setIs_signup(String is_signup) {
        this.is_signup = is_signup;
    }

    public String getIs_main() {
        return is_main;
    }

    public void setIs_main(String is_main) {
        this.is_main = is_main;
    }
}
