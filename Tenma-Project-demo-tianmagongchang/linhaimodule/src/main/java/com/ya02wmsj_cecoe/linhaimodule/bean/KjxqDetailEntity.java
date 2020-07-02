package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class KjxqDetailEntity implements Serializable {

    /**
     * images : []
     * clickCount : 4
     * headImage : http://thirdqq.qlogo.cn/g?b=oidb&k=8OEUh5aV4AP5Cc1yVTWFHg&s=100&t=1554992475
     * mobile : 15161531512
     * id : 46da0ab948464d31bf9150f8bb55e7d5
     * title : 测试
     * userid : 188569
     * content : 测试
     * createDate : 2020-03-12 10:47:54
     * username : 用户28984
     */
    private List<String> images;
    private int clickCount;
    private String headImage;
    private String mobile;
    private String id;
    private String title;
    private int userid;
    private String content;
    private String createDate;
    private String username;

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getImages() {
        return images;
    }

    public int getClickCount() {
        return clickCount;
    }

    public String getHeadImage() {
        return headImage;
    }

    public String getMobile() {
        return mobile;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getUserid() {
        return userid;
    }

    public String getContent() {
        return content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUsername() {
        return username;
    }
}
