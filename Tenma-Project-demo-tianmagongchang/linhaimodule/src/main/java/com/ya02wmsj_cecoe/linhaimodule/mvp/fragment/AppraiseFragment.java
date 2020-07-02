package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.AppraiseAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppraiseFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppraiseFragmentPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-07-29.
 */
public class AppraiseFragment extends BaseListFragment<AppraiseFragmentContract.Presenter> implements AppraiseFragmentContract.View {

    public static AppraiseFragment start(String status) {
        AppraiseFragment fragment = new AppraiseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new AppraiseAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new AppraiseFragmentPresenter(this);
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }

    @Override
    public void showActionThemeDialog() {

    }
}
