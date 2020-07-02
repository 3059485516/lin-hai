package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LiveListEntity implements Serializable {
    private String id;
    private String roomid;
    private String cid;
    private String ctime;
    private String wyy_uuid;
    private String name;
    private String type;
    private String type_name;
    private String status;
    private String icon;
    private String push_url;
    private String http_pull_url;
    private String hls_pull_url;
    private String rtmp_pull_url;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWyy_uuid() {
        return wyy_uuid;
    }

    public void setWyy_uuid(String wyy_uuid) {
        this.wyy_uuid = wyy_uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPush_url() {
        return push_url;
    }

    public void setPush_url(String push_url) {
        this.push_url = push_url;
    }

    public String getHttp_pull_url() {
        return http_pull_url;
    }

    public void setHttp_pull_url(String http_pull_url) {
        this.http_pull_url = http_pull_url;
    }

    public String getHls_pull_url() {
        return hls_pull_url;
    }

    public void setHls_pull_url(String hls_pull_url) {
        this.hls_pull_url = hls_pull_url;
    }

    public String getRtmp_pull_url() {
        return rtmp_pull_url;
    }

    public void setRtmp_pull_url(String rtmp_pull_url) {
        this.rtmp_pull_url = rtmp_pull_url;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
