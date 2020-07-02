package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.graphics.Color;

import com.ya02wmsj_cecoe.linhaimodule.adapter.KjcgAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.KjcgPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class KjcgFragment extends BaseListFragment<KjcgPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new KjcgAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new KjcgPresenter(this);
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        mRootView.setBackgroundColor(Color.WHITE);
    }
}
