package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OrganizationSubListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SeachOrganizeContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.SearchOrganizePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


/**
 * Created by BenyChan on 2019-08-01.
 */
public class SearchOrganizeActivity extends BaseListActivity<SeachOrganizeContract.Presenter> implements SeachOrganizeContract.View {
    protected EditText mEtSearch;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_search_organize;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new OrganizationSubListAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new SearchOrganizePresenter(this);
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected void initView() {
        setTitle("搜索");
        mEtSearch = findViewById(R.id.et_search);
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
        setRefreshEnabled(false);
        setNoDataText("没有搜索到相关内容");
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mEtSearch.getText())) {
                    mPresenter.search(mEtSearch.getText().toString());
                }
            }
        });
    }
}
