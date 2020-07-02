package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LtStreetEntity implements Serializable {

    /**
     * ca_name : 大洋街道礼堂分部
     * code : 331082002000
     * region_name : 大洋街道
     */
    private String ca_name;
    private String code;
    private String region_name;
    private boolean selected;

    public void setCa_name(String ca_name) {
        this.ca_name = ca_name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getCa_name() {
        return ca_name;
    }

    public String getCode() {
        return code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
