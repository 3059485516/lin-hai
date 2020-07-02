package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import com.ya02wmsj_cecoe.linhaimodule.adapter.ActionAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ActionPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class ActionFragment extends BaseListFragment<AListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ActionAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new ActionPresenter(this);
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
    }
}
