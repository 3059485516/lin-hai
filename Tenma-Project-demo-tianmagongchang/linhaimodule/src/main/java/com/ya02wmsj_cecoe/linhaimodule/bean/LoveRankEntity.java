package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class LoveRankEntity implements Serializable {

    /**
     * uuid : 953c125269ab4693bf48a944d8705b77
     * name : 彭杰
     * serve_time : 0.00
     * pic_url :
     */

    private String uuid;
    private String name;
    private String serve_time;
    private String pic_url;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServe_time() {
        return serve_time;
    }

    public void setServe_time(String serve_time) {
        this.serve_time = serve_time;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
