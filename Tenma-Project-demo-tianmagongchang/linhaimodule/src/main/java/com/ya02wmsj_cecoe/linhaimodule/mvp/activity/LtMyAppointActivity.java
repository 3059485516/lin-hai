package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.LtMyAppointAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtMyAppointPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;

public class LtMyAppointActivity extends BaseListActivity<AListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LtMyAppointAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtMyAppointPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我的预约");
        setMenuText("发起预约");
    }

    @Override
    public void onMenuClicked() {
        gotoActivity(LtChooseLtActivity.class);
    }
}
