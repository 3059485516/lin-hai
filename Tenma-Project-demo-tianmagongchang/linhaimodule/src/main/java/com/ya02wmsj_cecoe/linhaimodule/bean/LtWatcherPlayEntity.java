package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class LtWatcherPlayEntity implements Serializable {
    private String id;
    private String title;
    private String camera_type;
    private String camera_url;
    private String stream;
    private String vsea_u;
    private String vsea_p;
    private String camera_pic;
    private LtWatcherUrlEntity play_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCamera_type() {
        return camera_type;
    }

    public void setCamera_type(String camera_type) {
        this.camera_type = camera_type;
    }

    public String getCamera_url() {
        return camera_url;
    }

    public void setCamera_url(String camera_url) {
        this.camera_url = camera_url;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getVsea_u() {
        return vsea_u;
    }

    public void setVsea_u(String vsea_u) {
        this.vsea_u = vsea_u;
    }

    public String getVsea_p() {
        return vsea_p;
    }

    public void setVsea_p(String vsea_p) {
        this.vsea_p = vsea_p;
    }

    public String getCamera_pic() {
        return camera_pic;
    }

    public void setCamera_pic(String camera_pic) {
        this.camera_pic = camera_pic;
    }

    public LtWatcherUrlEntity getPlay_url() {
        return play_url;
    }

    public void setPlay_url(LtWatcherUrlEntity play_url) {
        this.play_url = play_url;
    }
}
