package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class KjcgEntity implements Serializable {
    private int totalpage;
    private int totalcount;
    private int pagesize;
    private int currentpage;

    private List<KjcgListEntity> newsList;

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public List<KjcgListEntity> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<KjcgListEntity> newsList) {
        this.newsList = newsList;
    }
}
