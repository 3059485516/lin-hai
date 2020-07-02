package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OrganizationSubListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationSubEntity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizationSubFragment extends BaseListFragment {

    public static OrganizationSubFragment start(List<OrganizationSubEntity> list) {
        OrganizationSubFragment fragment = new OrganizationSubFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_BEAN, (Serializable) list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new OrganizationSubListAdapter(mActivity, (List<OrganizationSubEntity>) getArguments().getSerializable(Constant.KEY_BEAN));
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }
}
