package com.ya02wmsj_cecoe.linhaimodule.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.Date;

public class ZhiyuanhuiEntity implements Serializable {
    private String distance;
    private String thumb;
    private String city;
    private String county;
    private String deptid;
    private int follow_num;
    private String title;
    private int recruit_people;
    private int join_num;
    private long recruit_finish_time;
    private int read_num;
    private String city_name;
    private int is_signup;
    private String province;
    private String deptheadurl;
    private String id;
    private int share_num;
    private int like_num;
    private String detailaddress;
    private long recruit_start_time;
    private String county_name;
    private String province_name;
    private String deptname;
    private int signup_people;
    private String categoryIds;
    private String location;
    private int is_finish;
    private String signupType;
    private String yl_status;

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRecruit_people(int recruit_people) {
        this.recruit_people = recruit_people;
    }

    public void setRecruit_finish_time(long recruit_finish_time) {
        this.recruit_finish_time = recruit_finish_time;
    }

    public void setRead_num(int read_num) {
        this.read_num = read_num;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setIs_signup(int is_signup) {
        this.is_signup = is_signup;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDeptheadurl(String deptheadurl) {
        this.deptheadurl = deptheadurl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public void setRecruit_start_time(long recruit_start_time) {
        this.recruit_start_time = recruit_start_time;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setSignup_people(int signup_people) {
        this.signup_people = signup_people;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIs_finish(int is_finish) {
        this.is_finish = is_finish;
    }

    public void setSignupType(String signupType) {
        this.signupType = signupType;
    }

    public String getDistance() {
        return distance;
    }

    public String getThumb() {
        return thumb;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getDeptid() {
        return deptid;
    }

    public int getFollow_num() {
        return follow_num;
    }

    public String getTitle() {
        return title;
    }

    public int getRecruit_people() {
        return recruit_people;
    }

    public long getRecruit_finish_time() {
        return recruit_finish_time;
    }

    public int getRead_num() {
        return read_num;
    }

    public String getCity_name() {
        return city_name;
    }

    public int getIs_signup() {
        return is_signup;
    }

    public String getProvince() {
        return province;
    }

    public String getDeptheadurl() {
        return deptheadurl;
    }

    public String getId() {
        return id;
    }

    public int getShare_num() {
        return share_num;
    }

    public int getLike_num() {
        return like_num;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public long getRecruit_start_time() {
        return recruit_start_time;
    }

    public String getCounty_name() {
        return county_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public String getDeptname() {
        return deptname;
    }

    public int getSignup_people() {
        return signup_people;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public String getLocation() {
        return location;
    }

    public int getIs_finish() {
        return is_finish;
    }

    public String getSignupType() {
        return signupType;
    }

    public String getYl_status() {
        return yl_status;
    }

    public void setYl_status(String yl_status) {
        this.yl_status = yl_status;
    }

    public int getJoin_num() {
        return join_num;
    }

    public void setJoin_num(int join_num) {
        this.join_num = join_num;
    }

    public String getStatus() {
        if (is_finish == 1) {
            return "已结束";
        } else {
            if (new Date().getTime() < recruit_start_time) {
                return "未开始";
            } else {
                if ("审核中".equals(yl_status)){
                    return "审核中";
                }else {
                    return "进行中";
                }
            }
        }
    }
}
