package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtChooseAreaAdapter2;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtChooseLtAdapter2;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtChooseLtContract2;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtChooseLtPresenter2;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class LtChooseLtActivity2 extends BaseActivity<LtChooseLtPresenter2> implements LtChooseLtContract2.View {
    private RecyclerView mRvArea, mRvLt;
    private LtChooseAreaAdapter2 mAreaAdapter;
    private LtChooseLtAdapter2 mLtAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_choose_2;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtChooseLtPresenter2(this);
    }

    @Override
    protected void initView() {
        setTitle("选择礼堂");
        mRvArea = findViewById(R.id.rv_area);
        mRvLt = findViewById(R.id.rc_lt);
        mRvArea.setLayoutManager(new LinearLayoutManager(this));
        mRvLt.setLayoutManager(new LinearLayoutManager(this));
        mRvArea.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, 0, 0));
        mRvLt.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, 0, 0));
        mAreaAdapter = new LtChooseAreaAdapter2(this, mPresenter.getAreaList(), new LtChooseAreaAdapter2.IClickAreaItemListener() {
            @Override
            public void onAreaItemClick(LtStreetEntity entity) {
                mPresenter.getLt(entity.getCode());
            }
        });
        mRvArea.setAdapter(mAreaAdapter);
        mLtAdapter = new LtChooseLtAdapter2(this, mPresenter.getLtList());
        mLtAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent();
                intent.putExtra(Constant.KEY_BEAN, mPresenter.getLtList().get(position));
                setResult(RESULT_OK, intent);
                finishActivity();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRvLt.setAdapter(mLtAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getArea();
    }

    @Override
    public void updateArea() {
        mAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateLt() {
        mLtAdapter.notifyDataSetChanged();
    }
}
