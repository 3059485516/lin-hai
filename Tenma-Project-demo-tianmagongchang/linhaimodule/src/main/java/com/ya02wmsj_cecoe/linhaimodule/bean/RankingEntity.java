package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

public class RankingEntity implements Serializable {

    /**
     * duration : 12.03
     * headimage : http://xx.com/xx.jpg
     * sex : 1
     * name : 张三
     * declaration : volunteer declaration
     */
    private double duration;
    private String headimage;
    private String sex;
    private String name;
    private String declaration;
    //组织相关
    private String deptid;
    private String deptname;
    private String headImageUrl;

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public double getDuration() {
        return duration;
    }

    public String getHeadimage() {
        return headimage;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public String getDeclaration() {
        return declaration;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}
