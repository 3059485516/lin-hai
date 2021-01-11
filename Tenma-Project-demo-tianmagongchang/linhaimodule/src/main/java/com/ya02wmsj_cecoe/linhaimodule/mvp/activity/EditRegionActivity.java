package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.widget.Button;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.User;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EditRegionContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.EditRegionPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import java.util.List;

/**
 * 绑定区域
 */
public class EditRegionActivity extends BaseActivity<EditRegionContract.Presenter> implements EditRegionContract.View {
    protected YLTextViewGroup mTvRegionCounty;
    protected YLTextViewGroup mTvRegionTown;
    protected YLTextViewGroup mTvRegionVillage;

    protected Button mBtnBindArea;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_bind_region;
    }

    @Override
    protected void initMVP() {
        mPresenter = new EditRegionPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("修改区域");
        mTvRegionCounty = findViewById(R.id.tv_area_1);
        mTvRegionTown = findViewById(R.id.tv_area_2);
        mTvRegionVillage = findViewById(R.id.tv_area_3);
        mBtnBindArea = findViewById(R.id.btn_bind);

        mBtnBindArea.setText("修改区域");

        User user = Config.getInstance().getUser();
        String county = user.getCounty();
        String county_name = user.getCounty_name();
        String town = user.getTown();
        String town_name = user.getTown_name();
        String village = user.getVillage();
        String village_name = user.getVillage_name();

        mTvRegionCounty.setTextRight(county_name);
        mTvRegionCounty.setTag(county);

        mTvRegionTown.setTextRight(town_name);
        mTvRegionTown.setTag(town);

        mTvRegionVillage.setTextRight(village_name);
        mTvRegionVillage.setTag(village);

        mTvRegionTown.setOnClickListener(v -> {
            if (mTvRegionCounty.getTag() == null) return;
            mPresenter.getRegionData2("town", (String) mTvRegionCounty.getTag());
        });

        mTvRegionVillage.setOnClickListener(v -> {
            if (mTvRegionTown.getTag() == null) return;
            mPresenter.getRegionData3("village", (String) mTvRegionTown.getTag());
        });

        mBtnBindArea.setOnClickListener(v -> {
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

    @Override
    public void updateSelectRegion() {
        User user = Config.getInstance().getUser();

        String county_name = mTvRegionCounty.getTextRight();
        String county = (String) mTvRegionCounty.getTag();

        String town_name = mTvRegionTown.getTextRight();
        String town = (String) mTvRegionTown.getTag();

        String village_name = mTvRegionVillage.getTextRight();
        String village = (String) mTvRegionVillage.getTag();

        user.setCounty(county);
        user.setCounty_name(county_name);

        user.setTown_name(town_name);
        user.setTown(town);

        user.setVillage_name(village_name);
        user.setVillage(village);
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
