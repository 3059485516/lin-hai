package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class ServiceInfoEntity implements Serializable {
    private String id;
    private String title;
    private String desc;
    private String category_id;
    private String serve_address;
    private String serve_time;
    private String organization;
    private String region_code;
    private String st_lnglat;
    private String ctime;
    private String category_name;
    private List<ServiceConcatEntity> serve_concat;
    private RegionInfo region_info;
    private String distance;

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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getServe_address() {
        return serve_address;
    }

    public void setServe_address(String serve_address) {
        this.serve_address = serve_address;
    }

    public String getServe_time() {
        return serve_time;
    }

    public void setServe_time(String serve_time) {
        this.serve_time = serve_time;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getSt_lnglat() {
        return st_lnglat;
    }

    public void setSt_lnglat(String st_lnglat) {
        this.st_lnglat = st_lnglat;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<ServiceConcatEntity> getServe_concat() {
        return serve_concat;
    }

    public void setServe_concat(List<ServiceConcatEntity> serve_concat) {
        this.serve_concat = serve_concat;
    }

    public RegionInfo getRegion_info() {
        return region_info;
    }

    public void setRegion_info(RegionInfo region_info) {
        this.region_info = region_info;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
