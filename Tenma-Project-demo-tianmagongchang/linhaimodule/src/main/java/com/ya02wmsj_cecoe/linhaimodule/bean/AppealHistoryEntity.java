package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class AppealHistoryEntity implements Serializable {

    /**
     * id : 2
     * title : 宣讲文明文化
     * code : bca2cec21f8a4dd383d0940fcdc5f8c6
     * uuid : c8660342c12c407db87c61e137f86cb2
     * ctime : 2019-07-30 14:40:12
     * item_id : 1
     * item_version : 20190529193902
     * cur_handler : e56a90bdb30447fba0f7243a9f840ba8,a5082ed418524a92859926e5a6ed3110
     * reject_reason :
     * region_code : 331082001001
     * region_info : {"county_name":"临海市","town_name":"古城街道","village_name":"文化社区"}
     * event_status : 待受理
     * event_source : 公众用户
     */

    private String id;
    private String title;
    private String code;
    private String uuid;
    private String ctime;
    private String item_id;
    private String item_version;
    private String cur_handler;
    private String reject_reason;
    private String region_code;
    private RegionInfo region_info;
    private String event_status;
    private String event_source;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_version() {
        return item_version;
    }

    public void setItem_version(String item_version) {
        this.item_version = item_version;
    }

    public String getCur_handler() {
        return cur_handler;
    }

    public void setCur_handler(String cur_handler) {
        this.cur_handler = cur_handler;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public RegionInfo getRegion_info() {
        return region_info;
    }

    public void setRegion_info(RegionInfo region_info) {
        this.region_info = region_info;
    }

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    public String getEvent_source() {
        return event_source;
    }

    public void setEvent_source(String event_source) {
        this.event_source = event_source;
    }

    public static class RegionInfoBean {
    }
}
