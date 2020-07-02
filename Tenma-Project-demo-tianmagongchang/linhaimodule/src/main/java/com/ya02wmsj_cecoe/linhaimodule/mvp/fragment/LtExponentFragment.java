package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtExponentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtExponentListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class LtExponentFragment extends BaseListFragment<LtExponentListPresenter> implements IListView {

    public static LtExponentFragment create(String code) {
        LtExponentFragment fragment = new LtExponentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LtExponentAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtExponentListPresenter(this, getArguments().getString(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }
}
