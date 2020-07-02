package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrginazeDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class OrginazeDetailPresenter extends OrginazeDetailContract.Presenter {

    public OrginazeDetailPresenter(OrginazeDetailContract.View view) {
        super(view);
    }

    public void joinOrginize(String depId) {
        addRx2Destroy(new RxSubscriber<Object>(Api.joinOrginize(depId)) {
            @Override
            protected void _onNext(Object o) {
                mView.toast("加入成功");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }

    @Override
    public void quitOrginize(String depId) {
        addRx2Destroy(new RxSubscriber<Object>(Api.quitOrginize(depId)) {
            @Override
            protected void _onNext(Object o) {
                mView.toast("退出成功");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }

    @Override
    public void getOrginazeDetail(String depId) {
        addRx2Destroy(new RxSubscriber<OrginazeDetailEntity>(Api.getZyhOrganDetail(depId)) {
            @Override
            protected void _onNext(OrginazeDetailEntity entity) {
                if (entity != null) {
                    mView.updateView(entity);
                }
            }
        });
    }
}
