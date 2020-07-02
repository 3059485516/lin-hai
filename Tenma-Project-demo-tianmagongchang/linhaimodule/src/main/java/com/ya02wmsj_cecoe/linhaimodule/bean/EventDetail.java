package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class EventDetail implements Serializable {

    /**
     * id : 2
     * title : 宣讲文明文化
     * desc : 哦哦哦哦哦
     * code : bca2cec21f8a4dd383d0940fcdc5f8c6
     * uuid : c8660342c12c407db87c61e137f86cb2
     * source : SOURCE001
     * status : STATUS001
     * ctime : 2019-07-30 14:40:12
     * item_id : 1
     * item_version : 20190529193902
     * cur_handler : e56a90bdb30447fba0f7243a9f840ba8,a5082ed418524a92859926e5a6ed3110
     * reject_reason :
     * region_code : 331082001001
     * name : 宣讲文明文化
     * process_info : [{"process":"2019-07-30 14:40:12 张超【文化社区实践站管理员】/张玉祁【文化社区实践站管理员】 处理中","desc":"","id":"4","item_process_id":"1","dealstatus":"0"}]
     */

    private String id;
    private String title;
    private String desc;
    private String code;
    private String uuid;
    private String source;
    private String status;
    private String ctime;
    private String item_id;
    private String item_version;
    private String cur_handler;
    private String reject_reason;
    private String region_code;
    private String name;
    private List<DealProcess> process_info;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DealProcess> getProcess_info() {
        return process_info;
    }

    public void setProcess_info(List<DealProcess> process_info) {
        this.process_info = process_info;
    }
}
