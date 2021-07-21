package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NetPersonBetContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * 网络惠民
 */
public class NetPersonBetPresenter extends NetPersonBetContract.Presenter {

    public NetPersonBetPresenter(NetPersonBetContract.View view) {
        super(view);
    }

    public void clickContent(String type) {
        addRx2Destroy(new RxSubscriber<String>(Api.clickedContent(type)) {
            @Override
            protected void _onNext(String str) {
            }

            @Override
            protected void _onError(String code) {
            }
        });
    }
}
