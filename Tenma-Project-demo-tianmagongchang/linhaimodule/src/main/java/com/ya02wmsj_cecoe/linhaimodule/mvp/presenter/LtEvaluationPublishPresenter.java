package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtEvaluationPublishContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;
import java.util.Map;

public class LtEvaluationPublishPresenter extends LtEvaluationPublishContract.Presenter {

    public LtEvaluationPublishPresenter(LtEvaluationPublishContract.View view) {
        super(view);
    }

    @Override
    public void caEvaApply(Map<String, Object> map) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.caEvaApply(map, getImageList()), mView) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.finishActivity();
            }
        });
    }

    @Override
    public void getEvaRules() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<LtEvaEntity>>(Api.getEvaRules(), mView) {
            @Override
            protected void _onNext(List<LtEvaEntity> list) {
                mView.dismissDialog();
                getRulesList().clear();
                getRulesList().addAll(list);
                mView.showRulesDialog();
            }
        });
    }
}
