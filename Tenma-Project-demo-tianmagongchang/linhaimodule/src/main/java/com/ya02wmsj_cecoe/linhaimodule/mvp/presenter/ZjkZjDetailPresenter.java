package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkZjDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZjkZjDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class ZjkZjDetailPresenter extends ZjkZjDetailContract.Presenter {
    public ZjkZjDetailPresenter(ZjkZjDetailContract.View view) {
        super(view);
    }

    @Override
    public void queryExpertDetail(String id) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<ZjkZjDetailEntity>(Api.queryExpertDetail(id)) {
            @Override
            protected void _onNext(ZjkZjDetailEntity entity) {
                mView.dismissDialog();
                if (entity != null) {
                    mView.updateView(entity);
                }
            }
        });
    }
}
