package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EduContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * 教育服务
 * Created by BenyChan on 2019-07-26.
 */
public class EduPresenter extends EduContract.Presenter {
    public EduPresenter(EduContract.View view) {
        super(view);
    }

    @Override
    public void clickContent() {
        addRx2Destroy(new RxSubscriber<String>(Api.clickedContent("教育服务")) {
            @Override
            protected void _onNext(String str) {
            }

            @Override
            protected void _onError(String code) {
            }
        });
    }
}
