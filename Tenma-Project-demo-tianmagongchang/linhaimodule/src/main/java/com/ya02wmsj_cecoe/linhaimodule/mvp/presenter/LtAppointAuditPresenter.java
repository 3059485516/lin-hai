package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class LtAppointAuditPresenter extends APresenter {
    public LtAppointAuditPresenter(IView view) {
        super(view);
    }

    public void auditSourceApply(String id, String status, String review_msg) {
        addRx2Destroy(new RxSubscriber<Object>(Api.auditSourceApply(id, status, review_msg)) {
            @Override
            protected void _onNext(Object o) {
                toast("操作成功");
                mView.finishActivity();
            }
        });
    }
}
