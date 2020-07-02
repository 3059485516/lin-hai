package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.AppraiseListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.UpdateRegionAppriaEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppraiseListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppraiseListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import io.reactivex.functions.Consumer;


/**
 * 议一议
 * Created by BenyChan on 2019-08-01.
 */
public class AppraiseListFragment extends BaseListFragment<AppraiseListPresenter> implements AppraiseListContract.View {
    RadioButton mTvVillage;

    RadioButton mTvTown;

    RadioButton mTvCountry;

    private int mRegionLevel = 0;   //0-全市，1-镇街， 2-社区


    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_appraise;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new AppraiseListAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new AppraiseListPresenter(this, RegionManager.getInstance().getCurrentCountyCode());
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
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

        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UpdateRegionAppriaEntity.class).subscribe(new Consumer<UpdateRegionAppriaEntity>() {
            @Override
            public void accept(UpdateRegionAppriaEntity updateRegionAppriaEntity) throws Exception {
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


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getPageData(true);
    }
}
