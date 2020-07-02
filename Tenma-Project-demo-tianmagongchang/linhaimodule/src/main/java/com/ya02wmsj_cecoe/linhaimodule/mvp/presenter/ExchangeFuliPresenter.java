package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ExchangeFuliContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class ExchangeFuliPresenter extends ExchangeFuliContract.Presenter {
    public ExchangeFuliPresenter(ExchangeFuliContract.View view) {
        super(view);
    }

    @Override
    public void getVolunteerScore() {
        addRx2Destroy(new RxSubscriber<String>(Api.getVolunteerScore()) {
            @Override
            protected void _onNext(String s) {
                if (!TextUtils.isEmpty(s)) {
                    mView.updateCoins(s);
                }
            }
        });
    }
}
