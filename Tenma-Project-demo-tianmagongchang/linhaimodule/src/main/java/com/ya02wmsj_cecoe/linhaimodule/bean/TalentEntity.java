package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class TalentEntity implements Serializable {

    /**
     * name : 李宁
     * region_id : 331082001001
     * ctime : 2020-01-09 17:00:02
     * region_name : 临海市古城街道文化社区
     * id : 1
     * team_id : 3
     * uuid : 9032e93611734120ad2346ade9a915be
     * team_name : 测试队伍
     * desc : 医疗救助
     */
    private String name;
    private String region_id;
    private String ctime;
    private String region_name;
    private String id;
    private String team_id;
    private String uuid;
    private String team_name;
    private String desc;
    private String pic_url;

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getRegion_id() {
        return region_id;
    }

    public String getCtime() {
        return ctime;
    }

    public String getRegion_name() {
        return region_name;
    }

    public String getId() {
        return id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
