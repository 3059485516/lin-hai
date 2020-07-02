package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.KjxqDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.KjcgDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class KjxqDetailPresenter extends KjcgDetailContract.Presenter {
    public KjxqDetailPresenter(KjcgDetailContract.View view) {
        super(view);
    }

    @Override
    public void getDetail(String id) {
        addRx2Destroy(new RxSubscriber<KjxqDetailEntity>(Api.getScienceDetail(id)) {
            @Override
            protected void _onNext(KjxqDetailEntity entity) {
                if (entity != null) {
                    mView.updateView(entity);
                }
            }
        });
    }
}
