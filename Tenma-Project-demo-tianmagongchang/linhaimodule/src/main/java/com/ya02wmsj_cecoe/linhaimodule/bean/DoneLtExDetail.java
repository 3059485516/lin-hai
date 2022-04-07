package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class DoneLtExDetail implements Serializable {
    private String id;
    private String ca_id;
    private String title;
    private String content;
    private String ctime;
    private int final_audit_point;

    public int getFinal_audit_point() {
        return final_audit_point;
    }

    public void setFinal_audit_point(int final_audit_point) {
        this.final_audit_point = final_audit_point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCa_id() {
        return ca_id;
    }

    public void setCa_id(String ca_id) {
        this.ca_id = ca_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
