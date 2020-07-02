package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.AdvanceTopAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TheoryMoreContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.TheoryMorePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class TheoryMoreActivity extends BaseListActivity<TheoryMoreContract.Presenter> implements TheoryMoreContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new AdvanceTopAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new TheoryMorePresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setTitle("活动预告");
        setDefaultItemDecoration();
    }
}
