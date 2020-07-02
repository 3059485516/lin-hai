package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import com.ya02wmsj_cecoe.linhaimodule.adapter.SportVenueAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SportVenueContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.SportVenuePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class SportVenueFragment extends BaseListFragment<SportVenueContract.Presenter> implements SportVenueContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new SportVenueAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new SportVenuePresenter(this);
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }
}
