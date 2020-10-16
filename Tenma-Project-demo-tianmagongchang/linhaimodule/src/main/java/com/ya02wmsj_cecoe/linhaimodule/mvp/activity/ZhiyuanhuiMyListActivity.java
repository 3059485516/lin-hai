package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ActionAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ZhiyuanhuiMyListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class ZhiyuanhuiMyListActivity extends BaseListActivity<ZhiyuanhuiMyListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ActionAdapter(this, mPresenter.getDataList() );
    }

    @Override
    protected void initMVP() {
        mPresenter = new ZhiyuanhuiMyListPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_wdbm;
    }

    @Override
    protected void initView() {
        setTitle("我的报名");
        setDefaultItemDecoration();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.getPageData(true);
        }
    }
}
