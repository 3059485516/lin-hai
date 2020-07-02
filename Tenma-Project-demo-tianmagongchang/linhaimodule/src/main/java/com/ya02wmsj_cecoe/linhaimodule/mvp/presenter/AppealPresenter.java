package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppealContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-27.
 */
public class AppealPresenter extends AppealContract.Presenter {

    public AppealPresenter(AppealContract.View view) {
        super(view);
    }

    @Override
    public void addContentAppeal(Map<String, Object> map) {
        mView.showDialog("正在提交数据，请稍候..");
        addRx2Destroy(new RxSubscriber<Object>(Api.addContentAppeal(map)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
