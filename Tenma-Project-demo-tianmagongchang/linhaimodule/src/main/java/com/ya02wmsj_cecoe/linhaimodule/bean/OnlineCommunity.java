package com.ya02wmsj_cecoe.linhaimodule.bean;

import com.google.android.exoplayer2.util.NalUnitUtil;

import java.io.Serializable;

/**
 * 网络社区
 */
public class OnlineCommunity implements Serializable {
    private String id;
    private String c_id;
    private String type;
    private NodeContent content;
    private AppraiseEntity activity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NodeContent getContent() {
        return content;
    }

    public void setContent(NodeContent content) {
        this.content = content;
    }

    public AppraiseEntity getActivity() {
        return activity;
    }

    public void setActivity(AppraiseEntity activity) {
        this.activity = activity;
    }

    public boolean isContent(){
        return content != null;
    }

    public boolean isActivity(){
        return activity != null;
    }
}
