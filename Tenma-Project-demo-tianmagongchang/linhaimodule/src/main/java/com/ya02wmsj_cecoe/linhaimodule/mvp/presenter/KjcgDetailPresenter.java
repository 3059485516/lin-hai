package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.KjcgDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.KjcgDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class KjcgDetailPresenter extends KjcgDetailContract.Presenter {
    public KjcgDetailPresenter(KjcgDetailContract.View view) {
        super(view);
    }

    @Override
    public void getDetail(String id) {
        addRx2Destroy(new RxSubscriber<KjcgDetailEntity>(Api.getScientificDetail(id)) {
            @Override
            protected void _onNext(KjcgDetailEntity entity) {
                if (entity != null) {
                    mView.updateView(entity);
                }
            }
        });
    }
}
