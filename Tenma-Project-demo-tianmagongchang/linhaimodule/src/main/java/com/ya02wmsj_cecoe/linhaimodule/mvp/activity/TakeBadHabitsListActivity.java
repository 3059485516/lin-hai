package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.TakeBadHabitsAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TakeBadHabitsListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.TakeBadHabitsListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class TakeBadHabitsListActivity extends BaseListActivity<TakeBadHabitsListContract.Presenter> implements TakeBadHabitsListContract.View {
    @Override
    protected void initMVP() {
        mPresenter = new TakeBadHabitsListPresenter(this);
    }

    @Override
    public void setLinearLayoutManager(){
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initView() {
        setTitle("我要拍陋习");
        setMenuIcon(R.mipmap.ya02wmsj_cecoe_take_bad_habits);
    }

    @Override
    public void onMenuClicked() {
        super.onMenuClicked();
        SelectVideoActivity.start(this, path -> UploadBadHabitsActivity.launch(TakeBadHabitsListActivity.this, path));
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new TakeBadHabitsAdapter(mContext,mPresenter.getDataList());
    }
}