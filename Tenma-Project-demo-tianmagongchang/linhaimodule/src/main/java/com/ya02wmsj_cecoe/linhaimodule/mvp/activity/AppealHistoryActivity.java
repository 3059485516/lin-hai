package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.AppealHistoryAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppealHistoryContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppealHistoryPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class AppealHistoryActivity extends BaseListActivity<AppealHistoryContract.Presenter> implements AppealHistoryContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new AppealHistoryAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new AppealHistoryPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("诉求历史");
        setDefaultItemDecoration();
    }
}
