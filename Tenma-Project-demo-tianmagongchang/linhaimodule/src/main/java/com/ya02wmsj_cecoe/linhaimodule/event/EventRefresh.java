package com.ya02wmsj_cecoe.linhaimodule.event;


public class EventRefresh {
    public static final int REFRESH_MEETING = 1;
    private int refreshLoc;

    public EventRefresh(int refreshLoc) {
        this.refreshLoc = refreshLoc;
    }

    public boolean isRefresh(int refreshLoc) {
        return refreshLoc == this.refreshLoc;
    }
}
