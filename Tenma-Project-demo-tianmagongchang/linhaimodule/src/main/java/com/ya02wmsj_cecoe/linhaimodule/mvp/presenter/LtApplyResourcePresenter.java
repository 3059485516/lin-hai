package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.Map;

public class LtApplyResourcePresenter extends APresenter {
    public LtApplyResourcePresenter(IView view) {
        super(view);
    }

    public void applyCASource(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<Object>(Api.applyCASource(map)) {
            @Override
            protected void _onNext(Object o) {
                toast("提交成功，等待审核");
                mView.finishActivity();
            }
        });
    }
}
