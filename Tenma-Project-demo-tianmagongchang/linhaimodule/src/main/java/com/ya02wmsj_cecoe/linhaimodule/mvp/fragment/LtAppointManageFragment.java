package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtMyAppointAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtAppointEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtAppointAuditActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtAppointListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class LtAppointManageFragment extends BaseListFragment<AListPresenter> implements IListView {

    public static LtAppointManageFragment create(String status) {
        LtAppointManageFragment fragment = new LtAppointManageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        LtMyAppointAdapter appointAdapter = new LtMyAppointAdapter(mActivity, mPresenter.getDataList());
        appointAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                LtAppointEntity entity = (LtAppointEntity) mPresenter.getDataList().get(position);
                Intent intent = new Intent(mActivity, LtAppointAuditActivity.class);
                intent.putExtra(Constant.KEY_BEAN, entity);
                gotoActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        return appointAdapter;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtAppointListPresenter(this, getArguments().getString(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.yl_graybg));
    }
}
