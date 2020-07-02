package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.BaseAreaListFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.BaseAreaListFragmentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.regionpicker.RegionPickDialog;

public abstract class BaseAreaListFragment extends BaseListFragment<BaseAreaListFragmentPresenter>
        implements BaseAreaListFragmentContract.View {
    protected RegionPickDialog mRegionPickDialog;

    protected abstract void updateRegion();

    protected void showRegionPickerDialog(View root) {
        if (mRegionPickDialog == null) {
            mRegionPickDialog = new RegionPickDialog(mActivity, RegionManager.getInstance().getTownList(), RegionManager.getInstance().getVillageList(), true);
            mRegionPickDialog.setOnParentClickListener((item, position) -> {
                RegionManager.getInstance().setCurrentTown(RegionManager.getInstance().getTownList().get(position));
                mPresenter.getRegionVillageData();  //获取村级数据
            });
            mRegionPickDialog.setOnChildClickListener((item, position) -> {
                RegionManager.getInstance().setCurrentVillage(RegionManager.getInstance().getVillageList().get(position));
                updateRegion();
                mRegionPickDialog.destroy();
            });
        }
        mRegionPickDialog.show(root);
    }


    @Override
    protected void initData() {
        mPresenter.getRegionCountyData();
    }

    @Override
    public void updateContry() {

    }

    @Override
    public void updateTown() {
        if (mRegionPickDialog != null) {
            mRegionPickDialog.setDataParentList(RegionManager.getInstance().getTownList());
        }
    }

    @Override
    public void updateVillage() {
        if (mRegionPickDialog != null) {
            mRegionPickDialog.setDataChildList(RegionManager.getInstance().getVillageList());
        }
    }

    @Override
    public void onDestroyView() {
        if (mRegionPickDialog != null) {
            mRegionPickDialog.destroy();
        }
        super.onDestroyView();
    }
}
