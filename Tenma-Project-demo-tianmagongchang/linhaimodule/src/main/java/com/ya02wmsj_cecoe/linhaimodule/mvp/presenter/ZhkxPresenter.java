package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZhkxContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class ZhkxPresenter extends ZhkxContract.Presenter {
    public ZhkxPresenter(ZhkxContract.View view) {
        super(view);
    }

    public void logToEBook() {
        addRx2Destroy(new RxSubscriber<String>(Api.chaoXingLogin()) {
            @Override
            protected void _onNext(String str) {
                mView.loginChaoxSuc(str);
            }

            @Override
            protected void _onError(String code) {
                toast("登录超星失败");
            }
        });
    }
}
