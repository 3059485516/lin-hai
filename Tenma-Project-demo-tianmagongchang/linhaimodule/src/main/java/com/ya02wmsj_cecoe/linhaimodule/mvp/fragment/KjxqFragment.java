package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.graphics.Color;

import com.ya02wmsj_cecoe.linhaimodule.adapter.KjxqAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.KjxqPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class KjxqFragment extends BaseListFragment<KjxqPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new KjxqAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new KjxqPresenter(this);
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        mRootView.setBackgroundColor(Color.WHITE);
    }
}
