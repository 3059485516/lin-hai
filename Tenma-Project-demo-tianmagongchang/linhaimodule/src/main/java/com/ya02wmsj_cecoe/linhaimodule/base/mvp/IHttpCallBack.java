package com.ya02wmsj_cecoe.linhaimodule.base.mvp;

/**
 * Created by 寻水的鱼 on 2018/7/24.
 */
public abstract class IHttpCallBack<T> {
    public abstract void onSucceed(T t);

    public void onError() {
    }
}
