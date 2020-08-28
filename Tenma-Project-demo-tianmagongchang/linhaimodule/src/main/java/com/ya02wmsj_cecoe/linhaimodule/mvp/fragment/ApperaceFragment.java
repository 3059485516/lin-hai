package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ApperaceAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActivityThemeEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.UpdateRegionAppriaEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppraiseFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppraiseFragmentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.ActionThemeDialog;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import io.reactivex.functions.Consumer;

/**
 * 评一评
 */
public class ApperaceFragment extends BaseListFragment<AppraiseFragmentPresenter>
        implements AppraiseFragmentContract.View, ActionThemeDialog.IActionThemeItemClick {
    LinearLayout mViewTop;

    RadioButton mTvVillage;

    RadioButton mTvTown;

    RadioButton mTvCountry;

    private ActionThemeDialog mDialog;
    private int mRegionLevel = 0;   //0-全市，1-镇街， 2-社区


    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_appraise;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ApperaceAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new AppraiseFragmentPresenter(this);
    }

    @Override
    protected void initView() {
        mViewTop = mRootView.findViewById(R.id.view_top);
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

    @Override
    public void showActionThemeDialog() {
        if (mDialog == null) {
            mDialog = new ActionThemeDialog(mActivity, this);
        }
    }

    @Override
    public void onClickActionThemeItem(ActivityThemeEntity entity) {
        mDialog.destroy();
        mPresenter.getPageData(true);
    }
}
