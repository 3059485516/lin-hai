package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class ActivityThemeEntity implements Serializable {

    /**
     * id : 1
     * text : 红黑榜
     */
    private String id;
    private String text;

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
