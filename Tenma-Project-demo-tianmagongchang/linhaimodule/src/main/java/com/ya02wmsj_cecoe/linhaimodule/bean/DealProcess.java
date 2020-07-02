package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class DealProcess implements Serializable {

    /**
     * process : 2019-07-30 14:40:12 张超【文化社区实践站管理员】/张玉祁【文化社区实践站管理员】 处理中
     * desc :
     * id : 4
     * item_process_id : 1
     * dealstatus : 0
     */

    private String process;
    private String desc;
    private String id;
    private String item_process_id;
    private String dealstatus;

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_process_id() {
        return item_process_id;
    }

    public void setItem_process_id(String item_process_id) {
        this.item_process_id = item_process_id;
    }

    public String getDealstatus() {
        return dealstatus;
    }

    public void setDealstatus(String dealstatus) {
        this.dealstatus = dealstatus;
    }
}
