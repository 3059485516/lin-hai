package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.ActionRecruitAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionRecruitContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ActionRecruitPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * 活动招募
 * Created by BenyChan on 2019-07-23.
 */
public class ActionRecruitActivity extends BaseListActivity<ActionRecruitContract.Presenter> implements ActionRecruitContract.View {

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ActionRecruitAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new ActionRecruitPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("活动招募");
        setDefaultItemDecoration();
    }
}
