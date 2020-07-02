package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class ActionDetailPresenter extends ActionDetailContract.Presenter {
    public ActionDetailPresenter(ActionDetailContract.View view) {
        super(view);
    }

    @Override
    public void activitySign(String action_id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.activitySign(action_id)) {
            @Override
            protected void _onNext(Object o) {
                toast("报名成功");
            }
        });
    }
}
