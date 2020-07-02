package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OrginazeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrginazePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class OrginazeActivity extends BaseListActivity<OrginazePresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        OrginazeAdapter adapter = new OrginazeAdapter(this, mPresenter.getDataList());
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                OrginazeListEntity entity = (OrginazeListEntity) mPresenter.getDataList().get(position);
                Intent intent = new Intent(mContext, OrginazeDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, entity.getId());
                mContext.startActivity(intent);
//                //  加组织
//                DialogHelp.getMessageDialog(mContext, "确定加入该组织吗？", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mPresenter.joinOrginize(entity.getId());
//                    }
//                }).show();
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
        mPresenter = new OrginazePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我要加团队");
        setDefaultItemDecoration();
        setLoadMoreEnabled(true);
    }
}
