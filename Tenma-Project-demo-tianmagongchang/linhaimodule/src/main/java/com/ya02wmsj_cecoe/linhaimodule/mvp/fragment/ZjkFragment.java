package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ZjkNodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ZjkzkAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkProfessionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZjkFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ZjkFragmentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.Serializable;
import java.util.List;

public class ZjkFragment extends BaseListFragment<ZjkFragmentContract.Presenter> implements ZjkFragmentContract.View {
    private RecyclerView mRvNode;

    public static ZjkFragment create(List<ZjkProfessionEntity> list) {
        ZjkFragment fragment = new ZjkFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_BEAN, (Serializable) list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_zjk_fragment;
    }

    @Override
    protected void initMVP() {
        mPresenter = new ZjkFragmentPresenter(this);
    }

    @Override
    protected void initView() {
        mRvNode = mRootView.findViewById(R.id.rv_node);
        mRvNode.setLayoutManager(new LinearLayoutManager(mActivity));
        setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        setItemDecoration(new StaggeredDividerItemDecoration(mActivity, 10, 2));
        List<ZjkProfessionEntity> node_list = (List<ZjkProfessionEntity>) getArguments().getSerializable(Constant.KEY_BEAN);
        if (node_list != null && node_list.size() > 0) {
            node_list.get(0).setSelected(true);
            ZjkNodeAdapter nodeAdapter = new ZjkNodeAdapter(mActivity, node_list, new ZjkNodeAdapter.IZjkNodeClickListener() {
                @Override
                public void onItemClickListener(ZjkProfessionEntity entity) {
                    mPresenter.setId(entity.getId());
                    mPresenter.getPageData(true);
                }
            });
            mRvNode.setAdapter(nodeAdapter);
            mPresenter.setId(node_list.get(0).getId());
            mPresenter.getPageData(true);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ZjkzkAdapter(mActivity, mPresenter.getDataList());
    }
}
