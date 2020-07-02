package com.ya02wmsj_cecoe.linhaimodule.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-23.
 */
public class User implements Serializable {

    /**
     * region_code :
     * token : B5N6IKx5GRuuUiQlnbEacVHFuyBggF80F2vMZch2QDQ=
     * uuid : c8660342c12c407db87c61e137f86cb2
     * name :
     * phone : 15706805989
     * first_login : y
     * weak_pwd : n
     * group_name : 公众用户
     * pic_url : /uploads/default/head.jpg
     */

    private String region_code;
    private String region_name;
    private String token;
    private String uuid;
    private String name;
    private String phone;
    private String first_login;
    private String weak_pwd;
    private String group_name;
    private String pic_url;
    private String county;
    private String county_name;
    private String town;
    private String town_name;
    private String village;
    private String village_name;
    private String group_id;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getFirst_login() {
        return first_login;
    }

    public void setFirst_login(String first_login) {
        this.first_login = first_login;
    }

    public String getWeak_pwd() {
        return weak_pwd;
    }

    public void setWeak_pwd(String weak_pwd) {
        this.weak_pwd = weak_pwd;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown_name() {
        return town_name;
    }

    public void setTown_name(String town_name) {
        this.town_name = town_name;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public boolean isLtManager() {
        if (TextUtils.isEmpty(group_id)) return false;
        return "24".equals(group_id) || "25".equals(group_id) || "26".equals(group_id);
    }
}
