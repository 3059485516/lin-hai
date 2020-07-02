package com.ya02wmsj_cecoe.linhaimodule.base.mvp;

import android.app.Activity;
import android.support.annotation.StringRes;

import com.ya02wmsj_cecoe.linhaimodule.rx.RxManager;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import io.reactivex.disposables.Disposable;


public abstract class APresenter<V extends IView> {
    protected V mView;
    public Activity mContext;
    private RxManager mRxManager2Stop;
    private RxManager mRxManager2Destroy;


    public APresenter(V view) {
        mView = view;
    }

    /**
     * 网络请求控制在生命周期OnStop
     */
    public void addRx2Stop(RxSubscriber rxSubscriber) {
        if (mRxManager2Stop == null) {
            mRxManager2Stop = new RxManager();
        }
        mRxManager2Stop.add(rxSubscriber);
    }

    public void addRx2Stop(Disposable disposable) {
        if (mRxManager2Stop == null) {
            mRxManager2Stop = new RxManager();
        }
        mRxManager2Stop.add(disposable);
    }

    public void onStop() {
        if (mRxManager2Stop != null) {
            mRxManager2Stop.clear();
            mRxManager2Stop = null;
        }
    }

    /**
     * 网络请求控制在生命周期onDestroy
     */
    public void addRx2Destroy(RxSubscriber rxSubscriber) {
        if (mRxManager2Destroy == null) {
            mRxManager2Destroy = new RxManager();
        }
        mRxManager2Destroy.add(rxSubscriber);
    }

    public void addRx2Destroy(Disposable disposable) {
        if (mRxManager2Destroy == null) {
            mRxManager2Destroy = new RxManager();
        }
        mRxManager2Destroy.add(disposable);
    }

    public void onDestroy() {

        if (mRxManager2Destroy != null) {
            mRxManager2Destroy.clear();
            mRxManager2Destroy = null;
        }
        mView = null;
        mContext = null;
    }

    public RxManager getRxManager2Destroy() {
        if (mRxManager2Destroy == null) {
            mRxManager2Destroy = new RxManager();
        }
        return mRxManager2Destroy;
    }

    protected void toast(String msg) {
        if (mView != null) {
            mView.toast(msg);
        }
    }

    protected void toast(@StringRes int resId) {
        if (mView != null) {
            mView.toast(resId);
        }
    }
}
