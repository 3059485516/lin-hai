package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class LtWatcherEntity implements Serializable {
    private String code;
    private String title;
    private int level;
    private List<LtWatcherEntity> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<LtWatcherEntity> getChildren() {
        return children;
    }

    public void setChildren(List<LtWatcherEntity> children) {
        this.children = children;
    }
}
