package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LtEntitiy implements Serializable {

    /**
     * level : 3
     * name : 府东社区文化礼堂
     * id : 4
     * pic :
     * region_code : 331082002001
     */
    private int level;
    private String name;
    private String id;
    private String pic;
    private String region_code;

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
}
