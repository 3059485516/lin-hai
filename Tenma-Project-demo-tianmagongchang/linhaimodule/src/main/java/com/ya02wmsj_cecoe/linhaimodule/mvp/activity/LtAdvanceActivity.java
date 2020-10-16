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
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtAdvancePresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.Map;


public class LtAdvanceActivity extends BaseActivity<LtAdvancePresenter> implements IView {
    private YLTextViewGroup mTvLt;
    private EditText mEtAdvance;
    private RecyclerView mRvPic;

    protected ImageAdapter mImgAdapter;
    private static final int REQUEST_LT_CODE = 1000;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_advance_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtAdvancePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("礼堂建议");
        setMenuText("我的建议");
        mTvLt = findViewById(R.id.tv_lt);
        mEtAdvance = findViewById(R.id.et_advance);
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
        findViewById(R.id.btn_commit).setOnClickListener(v -> {
            if (mTvLt.getTag() == null) {
                toast("请选择礼堂");
                return;
            }
            if (TextUtils.isEmpty(mEtAdvance.getText())) {
                toast("请填写建议");
                return;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("ca_id", mTvLt.getTag().toString());
            map.put("content", mEtAdvance.getText());
            mPresenter.addCAAdvance(map);
        });
        mTvLt.setOnClickListener(v -> startActivityForResult(new Intent(mContext, LtChooseLtActivity2.class), REQUEST_LT_CODE));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMenuClicked() {
        //  我的建议列表页面 LtMyAdvanceActivity
        gotoActivity(LtMyAdvanceActivity.class);
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
        } else if (requestCode == REQUEST_LT_CODE) {
            LtEntitiy entitiy = (LtEntitiy) data.getSerializableExtra(Constant.KEY_BEAN);
            mTvLt.setTextRight(entitiy.getName());
            mTvLt.setTag(entitiy.getId());
        }
    }
}
