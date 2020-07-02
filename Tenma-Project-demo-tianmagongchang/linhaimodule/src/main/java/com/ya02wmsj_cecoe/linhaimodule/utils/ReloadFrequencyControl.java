package com.ya02wmsj_cecoe.linhaimodule.utils;

public class ReloadFrequencyControl {
    boolean isReloading = false;
    boolean needReload = false;
    boolean reloadParam = false;

    public boolean canDoReload(boolean param) {
        if (isReloading) {
            // 正在加载，那么计划加载完后重载
            needReload = true;
            if (param) {
                reloadParam = true;
            }

            return false;
        } else {
            // 如果当前空闲，那么立即开始加载
            isReloading = true;
            return true;
        }
    }

    public boolean continueDoReloadWhenCompleted() {
        return needReload;
    }

    public void resetStatus() {
        isReloading = false;
        needReload = false;
        reloadParam = false;
    }

    public boolean getReloadParam() {
        return reloadParam;
    }
}
