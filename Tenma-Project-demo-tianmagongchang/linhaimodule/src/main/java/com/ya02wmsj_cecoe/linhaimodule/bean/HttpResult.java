package com.ya02wmsj_cecoe.linhaimodule.bean;

public class HttpResult<D> {
    private int code;
    private String desc;
    private D data;

    public int getResultCode() {
        return code;
    }

    public void setResultCode(int resultCode) {
        this.code = resultCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public D getResultValue() {
        return data;
    }

    public void setResultValue(D resultValue) {
        this.data = resultValue;
    }
}