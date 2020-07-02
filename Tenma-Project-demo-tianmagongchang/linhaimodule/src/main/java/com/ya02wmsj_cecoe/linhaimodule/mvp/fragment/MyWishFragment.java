package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.WishListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.MyWishListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.MyWishListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class MyWishFragment extends BaseListFragment<MyWishListContract.Presenter> implements MyWishListContract.View {

    public static MyWishFragment start(int type) {
        MyWishFragment fragment = new MyWishFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_INT_1, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new WishListAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new MyWishListPresenter(this, getArguments().getInt(Constant.KEY_INT_1));
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
    }
}
