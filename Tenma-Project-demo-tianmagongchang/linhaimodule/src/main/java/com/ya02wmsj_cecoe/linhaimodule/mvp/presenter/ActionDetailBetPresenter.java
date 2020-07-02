package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.ActionDetailBetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionDetailBetContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class ActionDetailBetPresenter extends ActionDetailBetContract.Presenter {
    public ActionDetailBetPresenter(ActionDetailBetContract.View view) {
        super(view);
    }

    @Override
    public void getRecruitDetail(String id) {
        addRx2Destroy(new RxSubscriber<ActionDetailBetEntity>(Api.getRecruitDetail(id)) {
            @Override
            protected void _onNext(ActionDetailBetEntity entity) {
                if (entity != null) {
                    mView.updateView(entity);
                }
            }
        });
    }

    @Override
    public void signupRecruit(String id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.signupRecruit(id)) {
            @Override
            protected void _onNext(Object o) {
                mView.toast("报名成功");
            }
        });
    }

    @Override
    public void signoutRecruit(String id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.signoutRecruit(id)) {
            @Override
            protected void _onNext(Object o) {
                mView.toast("取消报名成功");
            }
        });
    }
}
