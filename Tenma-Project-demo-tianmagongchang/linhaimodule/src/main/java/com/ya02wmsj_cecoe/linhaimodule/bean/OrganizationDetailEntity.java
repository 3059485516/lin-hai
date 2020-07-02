package com.ya02wmsj_cecoe.linhaimodule.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizationDetailEntity implements Serializable {

    /**
     * id : 43
     * pid : 0
     * name : “最多跑一次”宣讲志愿服务队
     * desc :
     * region_code :
     * latitude : 0.0000000
     * longitude : 0.0000000
     * phone :
     * address :
     * level : 0
     * category_id : 17
     * ctime : 2019-07-10 13:43:12
     * points : 0
     * unit :
     * charge_name :
     */

    private String id;
    private String pid;
    private String name;
    private String desc;
    private String region_code;
    private String latitude;
    private String longitude;
    private String phone;
    private String address;
    private String level;
    private String category_id;
    private String ctime;
    private String points;
    private String unit;
    private String charge_name;
    private List<OrganizationSubEntity> organ_practice;
    private List<OrganizationSubEntity> cultural_auditorium;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCharge_name() {
        return charge_name;
    }

    public void setCharge_name(String charge_name) {
        this.charge_name = charge_name;
    }

    public List<OrganizationSubEntity> getOrgan_practice() {
        return organ_practice;
    }

    public void setOrgan_practice(List<OrganizationSubEntity> organ_practice) {
        this.organ_practice = organ_practice;
    }

    public List<OrganizationSubEntity> getCultural_auditorium() {
        return cultural_auditorium;
    }

    public void setCultural_auditorium(List<OrganizationSubEntity> cultural_auditorium) {
        this.cultural_auditorium = cultural_auditorium;
    }
}
