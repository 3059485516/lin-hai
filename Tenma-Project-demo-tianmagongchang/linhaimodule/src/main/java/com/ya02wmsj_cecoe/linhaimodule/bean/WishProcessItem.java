package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishProcessItem implements Serializable {

    /**
     * title : 陈利华申报了心愿。
     * time : 2019-08-13 17:10:29
     */

    private String title;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
