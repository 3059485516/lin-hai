package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtShowAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;

public class LtShowFragment extends BaseListFragment<AListPresenter> implements IListView {
    public static LtShowFragment create(String code) {
        LtShowFragment fragment = new LtShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LtShowAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtListPresenter(this, getArguments().getString(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        setItemDecoration(new StaggeredDividerItemDecoration(mActivity, 10, 3));
        setLoadMoreEnabled(false);
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.yl_graybg));
    }
}
