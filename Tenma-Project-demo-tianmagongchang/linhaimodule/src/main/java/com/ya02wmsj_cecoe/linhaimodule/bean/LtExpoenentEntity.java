package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class LtExpoenentEntity implements Serializable {
    private int level;
    private String name;
    private String id;
    private String pic;
    private String region_code;
    private String points;
    private List<DoneLtExDetail> done_list;
    private boolean isExpand = false;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public List<DoneLtExDetail> getDone_list() {
        return done_list;
    }

    public void setDone_list(List<DoneLtExDetail> done_list) {
        this.done_list = done_list;
    }

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
