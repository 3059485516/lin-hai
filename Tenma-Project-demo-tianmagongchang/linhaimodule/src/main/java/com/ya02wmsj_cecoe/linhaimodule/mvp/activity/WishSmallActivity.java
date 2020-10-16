package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.WishSmallContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.WishSmallPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by BenyChan on 2019-08-14.
 */
public class WishSmallActivity extends BaseActivity<WishSmallContract.Presenter> implements WishSmallContract.View {
    protected YLEditTextGroup mEtName;

    protected YLEditTextGroup mEtPhone;

    protected YLTextViewGroup mTvRegion;

    protected YLEditTextGroup mEtAddress;

    protected YLEditTextGroup mEtTitle;

    protected EditText mEtDesc;

    protected TextView mTvTip;

    protected RecyclerView mRvPhoto;

    private ImageAdapter mImageAdapter;

    public static final int REQUEST_REGION_CODE = 400;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_wish_s;
    }

    @Override
    protected void initMVP() {
        mPresenter = new WishSmallPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("发布微心愿");
        setMenuText("我的心愿");
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mTvRegion = findViewById(R.id.tv_region);
        mEtAddress = findViewById(R.id.et_address);
        mEtTitle = findViewById(R.id.et_title);
        mEtDesc = findViewById(R.id.et_desc);
        mTvTip = findViewById(R.id.tv_tip);
        mRvPhoto = findViewById(R.id.rv_photo);


        String s = "请如实填写心愿申请人信息，我们收到心愿申请后会在1~3个工作日核实";
        mTvTip.setText(Html.fromHtml("<font width = 'auto' color='#FF0000'>*</font>" + s));
        mTvRegion.setOnClickListener(v -> startActivityForResult(new Intent(mContext, SelectRegionActivity.class), REQUEST_REGION_CODE));
        mRvPhoto.setLayoutManager(new GridLayoutManager(mContext, 4));
        mImageAdapter = new ImageAdapter(mContext, mPresenter.getImageList());
        mImageAdapter.setMax(9);
        mRvPhoto.setAdapter(mImageAdapter);
        mImageAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
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

        findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEtName.getTextRight())) {
                    toast("请填写姓名");
                    return;
                }
                if (TextUtils.isEmpty(mEtPhone.getTextRight())) {
                    toast("请填写电话");
                    return;
                }
                if (mTvRegion.getTag() == null) {
                    toast("请选择村社");
                    return;
                }
                if (TextUtils.isEmpty(mEtAddress.getTextRight())) {
                    toast("请填写详细地址");
                    return;
                }
                if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
                    toast("请填写标题");
                    return;
                }
                if (TextUtils.isEmpty(mEtDesc.getText())) {
                    toast("请填写心愿故事");
                    return;
                }
                //  提交
                Map<String, Object> map = new HashMap<>();
                map.put("name", mEtName.getTextRight());
                map.put("phone", mEtPhone.getTextRight());
                map.put("region_code", mTvRegion.getTag());
                map.put("address", mEtAddress.getTextRight());
                map.put("title", mEtTitle.getTextRight());
                map.put("desc", mEtDesc.getText());
                mPresenter.addTinyWish(map);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMenuClicked() {
        gotoActivity(MyWishActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PictureConfig.TYPE_IMAGE) {
            mPresenter.getImageList().clear();
            mPresenter.getImageList().addAll(PictureSelector.obtainMultipleResult(data));
            mImageAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_REGION_CODE) {
            mTvRegion.setTextRight(data.getStringExtra(Constant.KEY_STRING_1));
            mTvRegion.setTag(data.getStringExtra(Constant.KEY_STRING_2));
        }
    }
}
