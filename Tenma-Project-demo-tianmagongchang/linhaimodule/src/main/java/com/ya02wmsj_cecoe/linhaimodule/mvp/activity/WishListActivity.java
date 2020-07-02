package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.WishListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.WishListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.WishListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishListActivity extends BaseListActivity<WishListContract.Presenter> implements WishListContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new WishListAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new WishListPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我有微心愿");
        setDefaultItemDecoration();
        setMenuText("发布心愿");
    }

    @Override
    public void onMenuClicked() {
        gotoActivity(WishSmallActivity.class);
    }
}
