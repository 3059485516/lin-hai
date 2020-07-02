package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.PublishAppraiseContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-22.
 */
public class PublishAppraisePresenter extends PublishAppraiseContract.Presenter {
    public PublishAppraisePresenter(PublishAppraiseContract.View view) {
        super(view);
    }

    @Override
    public void commit(Map<String, String> map, List<String> imagePath) {
        mView.showDialog("正在提交，请稍候..");
        addRx2Destroy(new RxSubscriber<Object>(Api.commitAction(map, imagePath)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功！");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
                if (HttpCode.CODE_20012.getCode().equals(code)) {
                    mView.gotoActivity(new Intent(mContext, BindRegionActivity.class));
                }
            }
        });
    }
}
