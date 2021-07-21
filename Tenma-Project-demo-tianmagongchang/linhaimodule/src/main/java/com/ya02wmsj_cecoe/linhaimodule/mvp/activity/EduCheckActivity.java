package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.EduCheckAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.EduEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EduCheckContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.EduCheckPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * 教育查询
 */
public class EduCheckActivity extends BaseListActivity<EduCheckContract.Presenter> implements EduCheckContract.View {

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        EduCheckAdapter adapter = new EduCheckAdapter(this, mPresenter.getDataList());
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                EduEntity eduEntity = mPresenter.getDataList().get(position);
                if (eduEntity != null){
                    mPresenter.clickContent();
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, eduEntity.getTitle());
                    intent.putExtra(Constant.KEY_STRING_2, eduEntity.getLink());
                    mContext.startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        return adapter;
    }

    @Override
    protected void initMVP() {
        mPresenter = new EduCheckPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("教育查询");
        setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setBackgroundColor(Color.WHITE);
        mPresenter.clickContent();
    }
}
