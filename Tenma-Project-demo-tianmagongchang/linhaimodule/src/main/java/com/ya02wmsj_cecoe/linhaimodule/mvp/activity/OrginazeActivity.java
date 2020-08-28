package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OrginazeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrginazeContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrginazePresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class OrginazeActivity extends BaseListActivity<OrginazeContract.Presenter>implements OrginazeContract.View {
    protected ImageView ivSearch;
    protected EditText etSearch;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_orginaze;
    }

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
        etSearch = findViewById(R.id.et_search);
        ivSearch = findViewById(R.id.iv_search);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                KeyBoardUtils.closeSoftInput(mContext);
                mPresenter.getPageData(true);
            }
        });

        ivSearch.setOnClickListener(v -> {
            KeyBoardUtils.closeSoftInput(mContext);
            mPresenter.getPageData(true);
        });
    }

    @Override
    public String getSearchStr() {
        String searchStr = etSearch.getText().toString();
        if (TextUtils.isEmpty(searchStr)){
            searchStr = "";
        }
        return searchStr;
    }
}
