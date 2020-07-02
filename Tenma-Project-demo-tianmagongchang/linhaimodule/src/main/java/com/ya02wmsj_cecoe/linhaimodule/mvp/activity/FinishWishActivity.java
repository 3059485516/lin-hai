package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.FinishWishPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.Map;

public class FinishWishActivity extends BaseActivity<FinishWishPresenter> implements IView {
    private EditText mEtContent;
    private RecyclerView mRvPic;

    protected ImageAdapter mImgAdapter;
    private String mWishId;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_finish_wish_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new FinishWishPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("微心愿办结");
        mWishId = getIntent().getStringExtra(Constant.KEY_STRING_1);
        mEtContent = findViewById(R.id.et_content);
        mRvPic = findViewById(R.id.rv_pic);
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
                if (TextUtils.isEmpty(mEtContent.getText())) {
                    toast("请填写总结报告");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("wish_id", mWishId);
                map.put("tell", mEtContent.getText());
                mPresenter.commit(map);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
