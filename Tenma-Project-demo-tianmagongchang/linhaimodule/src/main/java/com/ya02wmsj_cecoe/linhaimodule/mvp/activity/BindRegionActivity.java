package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.BindRegionContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.BindRegionPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-27.
 */
public class BindRegionActivity extends BaseActivity<BindRegionContract.Presenter> implements BindRegionContract.View {
    protected YLTextViewGroup mTvRegionCounty;
    protected YLTextViewGroup mTvRegionTown;
    protected YLTextViewGroup mTvRegionVillage;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_bind_region;
    }

    @Override
    protected void initMVP() {
        mPresenter = new BindRegionPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("绑定区域");
        mTvRegionCounty = findViewById(R.id.tv_area_1);
        mTvRegionTown = findViewById(R.id.tv_area_2);
        mTvRegionVillage = findViewById(R.id.tv_area_3);

        mTvRegionTown.setOnClickListener(v -> {
            if (mTvRegionCounty.getTag() == null) return;
            mPresenter.getRegionData2("town", (String) mTvRegionCounty.getTag());
        });

        mTvRegionVillage.setOnClickListener(v -> {
            if (mTvRegionTown.getTag() == null) return;
            mPresenter.getRegionData3("village", (String) mTvRegionTown.getTag());
        });

        findViewById(R.id.btn_bind).setOnClickListener(v -> {
            if (mTvRegionVillage.getTag() == null) {
                toast("请选择完整区域");
                return;
            }
            mPresenter.bindArea((String) mTvRegionVillage.getTag());
        });
    }

    @Override
    protected void initData() {
        mPresenter.getRegionData1();
    }

    @Override
    public void updateRegion1(RegionEntity entity) {
        mTvRegionCounty.setTextRight(entity.getName());
        mTvRegionCounty.setTag(entity.getCode());
    }

    @Override
    public void updateRegion2(List<RegionEntity> list) {
        showRegionPickDialog2(list, mTvRegionTown);
    }

    @Override
    public void updateRegion3(List<RegionEntity> list) {
        showRegionPickDialog3(list, mTvRegionVillage);
    }

    private void showRegionPickDialog2(List<RegionEntity> list, YLTextViewGroup textViewGroup) {
        KeyBoardUtils.closeSoftInput(this);
        OptionsPickerView optionPicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            textViewGroup.setTextRight(list.get(options1).getName());
            textViewGroup.setTag(list.get(options1).getCode());
            mTvRegionVillage.setTextRight("");
            mTvRegionVillage.setTag(null);
        }).build();
        optionPicker.setPicker(list);
        if (!optionPicker.isShowing()) {
            optionPicker.show();
        }
    }

    private void showRegionPickDialog3(List<RegionEntity> list, YLTextViewGroup textViewGroup) {
        KeyBoardUtils.closeSoftInput(this);
        OptionsPickerView optionPicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            textViewGroup.setTextRight(list.get(options1).getName());
            textViewGroup.setTag(list.get(options1).getCode());
        }).build();
        optionPicker.setPicker(list);
        if (!optionPicker.isShowing()) {
            optionPicker.show();
        }
    }
}
