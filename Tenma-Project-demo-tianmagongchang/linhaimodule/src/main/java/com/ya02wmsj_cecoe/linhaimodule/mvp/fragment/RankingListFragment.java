package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LoveRankAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.RankingListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class RankingListFragment extends BaseListFragment<AListPresenter> implements IListView {

    public static RankingListFragment start(boolean orginaze) {
        RankingListFragment fragment = new RankingListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.KEY_BOOLEAN_1, orginaze);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LoveRankAdapter(mActivity, mPresenter.getDataList(), getArguments().getBoolean(Constant.KEY_BOOLEAN_1));
    }

    @Override
    protected void initMVP() {
        mPresenter = new RankingListPresenter(this, getArguments().getBoolean(Constant.KEY_BOOLEAN_1));
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
    }
}
