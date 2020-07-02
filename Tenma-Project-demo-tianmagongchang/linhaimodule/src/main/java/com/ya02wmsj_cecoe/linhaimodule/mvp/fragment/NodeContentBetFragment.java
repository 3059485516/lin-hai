package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RadioButton;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.MainNodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.UpdateRegionMyEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentBetPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import io.reactivex.functions.Consumer;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class NodeContentBetFragment extends BaseListFragment<NodeContentBetPresenter>
        implements IListView {

    RadioButton mTvVillage;

    RadioButton mTvTown;

    RadioButton mTvCountry;

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
        return new MainNodeContentAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new NodeContentBetPresenter(this, getArguments().getString(Constant.KEY_STRING_1));
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

        mTvCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegionLevel = 0;
                mPresenter.setRegionCode(RegionManager.getInstance().getCurrentCountyCode());
                mPresenter.getPageData(true);
            }
        });
        mTvTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegionLevel = 1;
                mPresenter.setRegionCode(RegionManager.getInstance().getCurrentTownCode());
                mPresenter.getPageData(true);
            }
        });
        mTvVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegionLevel = 2;
                mPresenter.setRegionCode(RegionManager.getInstance().getCurrentVillageCode());
                mPresenter.getPageData(true);
            }
        });

        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UpdateRegionMyEntity.class).subscribe(new Consumer<UpdateRegionMyEntity>() {
            @Override
            public void accept(UpdateRegionMyEntity updateRegionMyEntity) throws Exception {
                if (mRegionLevel == 0) {
                    mPresenter.setRegionCode(RegionManager.getInstance().getCurrentCountyCode());
                } else if (mRegionLevel == 1) {
                    mPresenter.setRegionCode(RegionManager.getInstance().getCurrentTownCode());
                } else {
                    mPresenter.setRegionCode(RegionManager.getInstance().getCurrentVillageCode());
                }
                mPresenter.getPageData(true);
            }
        }));
    }
}
