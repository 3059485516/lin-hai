package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizeTreeEntity extends OrganizationSubEntity {

    /**
     * id : 21
     * name : 文化社区实践站
     * category_id : 331082001001
     * pid : 331082001000
     * level : 3
     */
    private String category_id;
    private String pid;
    private String level;
    private List<OrganizeTreeEntity> list;


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<OrganizeTreeEntity> getList() {
        return list;
    }

    public void setList(List<OrganizeTreeEntity> list) {
        this.list = list;
    }
}
