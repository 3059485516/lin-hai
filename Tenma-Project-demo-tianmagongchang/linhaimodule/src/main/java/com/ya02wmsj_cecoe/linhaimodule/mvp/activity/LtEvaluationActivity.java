package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.LtEvaMainAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EvaluationContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.EvaluationPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class LtEvaluationActivity extends BaseListActivity<EvaluationPresenter> implements EvaluationContract.View {
//    @Override
//    protected int getLayoutId() {
//        return R.layout.ya02wmsj_cecoe_evaluation_activity;
//    }

    @Override
    protected void initMVP() {
        mPresenter = new EvaluationPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("测评记录");
        setMenuText("上报");
    }


    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LtEvaMainAdapter(mContext, mPresenter.getDataList());
    }

    @Override
    public void onMenuClicked() {
        gotoActivity(LtEvaluationPublishActivity.class);
    }
}
