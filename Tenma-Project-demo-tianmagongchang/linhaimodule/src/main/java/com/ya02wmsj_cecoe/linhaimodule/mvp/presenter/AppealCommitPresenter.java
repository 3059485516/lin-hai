package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppealCommitContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.Map;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class AppealCommitPresenter extends AppealCommitContract.Presenter {
    public AppealCommitPresenter(AppealCommitContract.View view) {
        super(view);
    }

    @Override
    public void addEvent(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<Object>(Api.addEvent(map)) {
            @Override
            protected void _onNext(Object o) {
                toast("提交成功");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                if ("20006".equals(code)) {
                    mView.gotoActivity(new Intent(mContext, BindRegionActivity.class));
                }
            }
        });
    }
}
