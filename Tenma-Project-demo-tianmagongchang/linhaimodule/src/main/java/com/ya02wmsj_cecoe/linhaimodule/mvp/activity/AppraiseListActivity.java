package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.AppraiseAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppraiseFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppraiseFragmentPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-07-29.
 */
public class AppraiseListActivity extends BaseListActivity<AppraiseFragmentContract.Presenter> implements AppraiseFragmentContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new AppraiseAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new AppraiseFragmentPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("评议预告");
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }

    @Override
    public void showActionThemeDialog() {

    }
}
