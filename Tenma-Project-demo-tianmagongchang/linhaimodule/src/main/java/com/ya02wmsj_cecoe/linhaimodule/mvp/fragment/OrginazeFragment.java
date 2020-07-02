package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import com.ya02wmsj_cecoe.linhaimodule.adapter.OrginazeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrginazePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class OrginazeFragment extends BaseListFragment<AListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new OrginazeAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new OrginazePresenter(this);
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        setLoadMoreEnabled(true);
    }
}
