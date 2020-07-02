package com.tenma.ventures.navigation.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 模块配置信息
 * Created by bin on 2017/12/26.
 */

public class TopNavigationBean {

    /**
     * key : ct_0
     * native : true
     * androidSrc :
     * iosSrc : Comp1ViewController
     * wwwFolderIos :
     * wwwFolderAndroid :
     * title : 地图
     * image :
     * isInterceptScroll:true
     */

    private String key;
    @SerializedName("native")
    private boolean nativeX;
    private String androidSrc;
    private String iosSrc;
    private String wwwFolderIos;
    private String wwwFolderAndroid;
    private String title;
    private String image;
    private boolean isInterceptScroll;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isNativeX() {
        return nativeX;
    }

    public void setNativeX(boolean nativeX) {
        this.nativeX = nativeX;
    }

    public String getAndroidSrc() {
        return androidSrc;
    }

    public void setAndroidSrc(String androidSrc) {
        this.androidSrc = androidSrc;
    }

    public String getIosSrc() {
        return iosSrc;
    }

    public void setIosSrc(String iosSrc) {
        this.iosSrc = iosSrc;
    }

    public String getWwwFolderIos() {
        return wwwFolderIos;
    }

    public void setWwwFolderIos(String wwwFolderIos) {
        this.wwwFolderIos = wwwFolderIos;
    }

    public String getWwwFolderAndroid() {
        return wwwFolderAndroid;
    }

    public void setWwwFolderAndroid(String wwwFolderAndroid) {
        this.wwwFolderAndroid = wwwFolderAndroid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isInterceptScroll() {
        return isInterceptScroll;
    }

    public void setInterceptScroll(boolean interceptScroll) {
        isInterceptScroll = interceptScroll;
    }
}
