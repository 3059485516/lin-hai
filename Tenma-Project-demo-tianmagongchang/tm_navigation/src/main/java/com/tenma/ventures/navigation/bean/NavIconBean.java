package com.tenma.ventures.navigation.bean;

/**
 * Created by wcc on 2018/9/22.
 */

public class NavIconBean {
    private String iconNum;
    private String iconCode;

    public NavIconBean(String iconNum, String iconCode) {
        this.iconNum = iconNum;
        this.iconCode = iconCode;
    }

    public String getIconNum() {
        return iconNum;
    }

    public String getIconCode() {
        return iconCode;
    }
}
