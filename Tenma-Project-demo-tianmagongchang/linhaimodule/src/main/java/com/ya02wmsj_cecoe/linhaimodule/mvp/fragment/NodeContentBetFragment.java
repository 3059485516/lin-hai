package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.RadioButton;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OnlineCommunityAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.UpdateRegionMyEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OnlineCommunityContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OnlineCommunityPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

/**
 * 网络社区 界面
 */
public class NodeContentBetFragment extends BaseListFragment<OnlineCommunityContract.Presenter> implements OnlineCommunityContract.View {
    protected RadioButton mTvVillage;
    protected RadioButton mTvTown;
    protected RadioButton mTvCountry;
    private int mRegionLevel = 0;   //0-全市，1-镇街，2-社区

    public static NodeContentBetFragment start(String nodeId) {
        NodeContentBetFragment fragment = new NodeContentBetFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, nodeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_xiuyixiu;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        OnlineCommunityAdapter adapter = new OnlineCommunityAdapter(mActivity, mPresenter.getDataList());
        adapter.setNodeId(nodeId);
        return adapter;
    }

    private String nodeId;

    @Override
    protected void initMVP() {
        nodeId = getArguments().getString(Constant.KEY_STRING_1);
        mPresenter = new OnlineCommunityPresenter(this,nodeId);
    }

    @Override
    protected void initView() {
        setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        setLoadMoreEnabled(true);
        setItemDecoration(new StaggeredDividerItemDecoration(mActivity, 10, 2));
        setMargin(10);
        mTvVillage = mRootView.findViewById(R.id.tv_village);
        mTvTown = mRootView.findViewById(R.id.tv_town);
        mTvCountry = mRootView.findViewById(R.id.tv_country);
        mTvCountry.setChecked(true);

        mTvCountry.setOnClickListener(v -> {
            mRegionLevel = 0;
            mPresenter.setRegionCode(RegionManager.getInstance().getCurrentCountyCode());
            mPresenter.getPageData(true);
        });
        mTvTown.setOnClickListener(v -> {
            mRegionLevel = 1;
            mPresenter.setRegionCode(RegionManager.getInstance().getCurrentTownCode());
            mPresenter.getPageData(true);
        });
        mTvVillage.setOnClickListener(v -> {
            mRegionLevel = 2;
            mPresenter.setRegionCode(RegionManager.getInstance().getCurrentVillageCode());
            mPresenter.getPageData(true);
        });

        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UpdateRegionMyEntity.class).subscribe(updateRegionMyEntity -> {
            if (mRegionLevel == 0) {
                mPresenter.setRegionCode(RegionManager.getInstance().getCurrentCountyCode());
            } else if (mRegionLevel == 1) {
                mPresenter.setRegionCode(RegionManager.getInstance().getCurrentTownCode());
            } else {
                mPresenter.setRegionCode(RegionManager.getInstance().getCurrentVillageCode());
            }
            mPresenter.getPageData(true);
        }));
    }
}
