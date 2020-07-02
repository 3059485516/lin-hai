package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-08-02.
 */
public class OrderHistoryEntity implements Serializable {

    /**
     * id : 94
     * region_code : 331082001001
     * ctime : 2019-07-27 16:45:38
     * status : 活动已结束
     * start_time : 0000-00-00 00:00:00
     * end_time : 0000-00-00 00:00:00
     * service_name : 送书法活动
     */

    private String id;
    private String region_code;
    private String ctime;
    private String order_status;
    private String start_time;
    private String end_time;
    private String service_name;
    private RegionInfo region_info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getStatus() {
        return order_status;
    }

    public void setStatus(String status) {
        this.order_status = status;
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

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public RegionInfo getRegion_info() {
        return region_info;
    }

    public void setRegion_info(RegionInfo region_info) {
        this.region_info = region_info;
    }
}
