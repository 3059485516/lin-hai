package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class RegionInfo implements Serializable {

    /**
     * county_name : 临海市
     * town_name : 古城街道
     * village_name : 文化社区
     */

    private String county_name;
    private String town_name;
    private String village_name;

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public String getTown_name() {
        return town_name;
    }

    public void setTown_name(String town_name) {
        this.town_name = town_name;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    @Override
    public String toString() {
        return (county_name == null ? "" : county_name) + (town_name == null ? "" : town_name) + (village_name == null ? "" : village_name);
    }
}
