package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.AdvanceTopAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TheoryContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.TheoryPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


/**
 * Created by BenyChan on 2019-07-26.
 */
public class TheoryActivity extends BaseListActivity<TheoryContract.Presenter> implements TheoryContract.View {
    protected RecyclerView mRvAction;

    private AdvanceTopAdapter mTopAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_theory;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new TheoryPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_2));
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setDefaultItemDecoration();
        mRvAction = findViewById(R.id.rv_action);
        mRvAction.setLayoutManager(new LinearLayoutManager(this));
        mRvAction.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, 0, 0));
        mTopAdapter = new AdvanceTopAdapter(this, mPresenter.getAdvanceTopList());
        mRvAction.setAdapter(mTopAdapter);

        findViewById(R.id.lay_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 展开更多
                Intent intent = new Intent(mContext, TheoryMoreActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, getIntent().getStringExtra(Constant.KEY_STRING_2));
                gotoActivity(intent);
            }
        });
    }

    @Override
    public void updateActionList() {
        mTopAdapter.notifyDataSetChanged();
    }
}
