package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LtExpoenentEntity implements Serializable {

    /**
     * level : 3
     * name : 东湖村文化礼堂
     * id : 13
     * pic : http://47.99.86.101:8040//uploads/ya02lhwhlt_wdhaw/pic/20200320/ab8515b1c877244c92f6bb947144b01c.jpg
     * region_code : 331082001202
     * points : 0
     */
    private int level;
    private String name;
    private String id;
    private String pic;
    private String region_code;
    private String points;

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getRegion_code() {
        return region_code;
    }

    public String getPoints() {
        return points;
    }
}
