package com.ya02wmsj_cecoe.linhaimodule.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class MainNodeEntity {
    @DrawableRes
    private int resId;

    private String text;

    private int code;

    private Class cls;

    public MainNodeEntity(@DrawableRes int resId, String text, int code, Class cls) {
        this.resId = resId;
        this.text = text;
        this.code = code;
        this.cls = cls;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }
}
