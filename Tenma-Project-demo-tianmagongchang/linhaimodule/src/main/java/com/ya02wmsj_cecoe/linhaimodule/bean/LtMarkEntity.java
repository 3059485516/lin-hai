package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LtMarkEntity implements Serializable {

    /**
     * id : 4
     * title : 乡村风貌
     */
    private String id;
    private String title;
    private boolean selected;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
