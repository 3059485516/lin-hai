package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class EduEntity implements Serializable {

    /**
     * display : 1
     * icon : http://218.108.32.131:8052//uploads/pic/20200115/1579076539_658887652.png
     * link : https://www.baidu.com
     * ctime : 2020-01-15 16:22:23
     * id : 4
     * sort : 99
     * title : 百度
     * desc :
     */
    private String display;
    private String icon;
    private String link;
    private String ctime;
    private String id;
    private String sort;
    private String title;
    private String desc;

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDisplay() {
        return display;
    }

    public String getIcon() {
        return icon;
    }

    public String getLink() {
        return link;
    }

    public String getCtime() {
        return ctime;
    }

    public String getId() {
        return id;
    }

    public String getSort() {
        return sort;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
