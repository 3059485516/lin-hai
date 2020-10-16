package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SocialCollectContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.SocialCollectPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialCollectActivity extends BaseActivity<SocialCollectContract.Presenter> implements SocialCollectContract.View {
    YLEditTextGroup mEtTitle;

    YLEditTextGroup mEtName;

    YLEditTextGroup mEtPhone;

    YLEditTextGroup mEtEmail;

    YLEditTextGroup mEtDetailAddr;

    YLTextViewGroup mTvCountry;

    YLTextViewGroup mTvTown;

    YLTextViewGroup mTvVillage;

    YLTextViewGroup mTvStatus;

    EditText mEtContent;

    RecyclerView mRvPic;

    protected ImageAdapter mImgAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_social_collect;
    }

    @Override
    protected void initMVP() {
        mPresenter = new SocialCollectPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("社情收集");
        mEtTitle = findViewById(R.id.et_title);
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mEtEmail = findViewById(R.id.et_email);
        mEtDetailAddr = findViewById(R.id.et_detail_addr);
        mTvCountry = findViewById(R.id.tv_country);
        mTvTown = findViewById(R.id.tv_town);
        mTvVillage = findViewById(R.id.tv_village);
        mTvStatus = findViewById(R.id.tv_status);
        mEtContent = findViewById(R.id.et_content);
        mRvPic = findViewById(R.id.rv_pic);

        mTvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSinglePicker(Arrays.asList("是", "否"), mTvStatus);
            }
        });
        mTvTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvCountry.getTag() == null) return;
                mPresenter.getRegionDataTown("town", (String) mTvCountry.getTag());
            }
        });
        mTvVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvTown.getTag() == null) return;
                mPresenter.getRegionDataVillage("village", (String) mTvTown.getTag());
            }
        });
        mRvPic.setLayoutManager(new GridLayoutManager(this, 4));
        mImgAdapter = new ImageAdapter(this, mPresenter.getImageList());
        mImgAdapter.setMax(9);
        mRvPic.setAdapter(mImgAdapter);
        mImgAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int id = view.getId();
                if (R.id.iv_image == id) {
                    PictureSelectorUtils.getImageMultipleOption(mContext, mPresenter.getImageList(), 9, PictureConfig.TYPE_IMAGE);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
                    toast("标题不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mEtName.getTextRight())) {
                    toast("姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mEtPhone.getTextRight())) {
                    toast("手机号码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mEtEmail.getTextRight())) {
                    toast("邮箱不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mEtDetailAddr.getTextRight())) {
                    toast("详细地址不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mEtContent.getText())) {
                    toast("内容不能为空");
                    return;
                }
                if (mTvVillage.getTag() == null) {
                    toast("地址不能为空");
                    return;
                }
                Map map = new HashMap<String, Object>();
                map.put("title", mEtTitle.getTextRight());
                map.put("content", mEtContent.getText());
                map.put("name", mEtName.getTextRight());
                map.put("concat", mEtPhone.getTextRight());
                map.put("email", mEtEmail.getTextRight());
                map.put("address", mTvCountry.getTextRight() + mTvTown.getTextRight() + mTvVillage.getTextRight());
                map.put("address_detail", mEtDetailAddr.getTextRight());
                map.put("is_open", "是".equals(mTvStatus.getTextRight()) ? "y" : "n");
                mPresenter.collectNetInfo(map);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getRegionDataCountry();
    }


    @Override
    public void updateRegionContry(RegionEntity entity) {
        mTvCountry.setTextRight(entity.getName());
        mTvCountry.setTag(entity.getCode());
    }

    @Override
    public void updateTown(List<RegionEntity> list) {
        showRegionPickDialogTown(list, mTvTown);
    }

    @Override
    public void updateVillage(List<RegionEntity> list) {
        showRegionPickDialogVillage(list, mTvVillage);
    }

    @Override
    public void uploadComplete() {
        finishActivity();
    }

    private void showRegionPickDialogTown(List<RegionEntity> list, YLTextViewGroup textViewGroup) {
        KeyBoardUtils.closeSoftInput(this);
        OptionsPickerView optionPicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            textViewGroup.setTextRight(list.get(options1).getName());
            textViewGroup.setTag(list.get(options1).getCode());
            mTvVillage.setTextRight("");
            mTvVillage.setTag(null);

        }).build();
        optionPicker.setPicker(list);
        if (!optionPicker.isShowing()) {
            optionPicker.show();
        }
    }

    private void showRegionPickDialogVillage(List<RegionEntity> list, YLTextViewGroup textViewGroup) {
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

    private void showSinglePicker(List<String> list, YLTextViewGroup textViewGroup) {
        KeyBoardUtils.closeSoftInput(this);
        OptionsPickerView optionPicker = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            textViewGroup.setTextRight(list.get(options1));
            textViewGroup.setTag(list.get(options1));

        }).build();
        optionPicker.setPicker(list);
        if (!optionPicker.isShowing()) {
            optionPicker.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PictureConfig.TYPE_IMAGE) {
            mPresenter.getImageList().clear();
            mPresenter.getImageList().addAll(PictureSelector.obtainMultipleResult(data));
            mImgAdapter.notifyDataSetChanged();
        }
    }
}
