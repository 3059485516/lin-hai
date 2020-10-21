package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.PublishOpinionContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.PublishOpinionPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.CircleProgressDialog;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class PublishOpinionActivity extends BaseActivity<PublishOpinionContract.Presenter> implements PublishOpinionContract.View {
    protected YLEditTextGroup mEtTitle;
    protected EditText mEtDesc;
    protected RecyclerView mRvPic;
    protected ImageAdapter mImgAdapter;
    protected ImageView iv_thumbnail;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_opinion;
    }

    @Override
    protected void initMVP() {
        mPresenter = new PublishOpinionPresenter(this, getIntent());
    }

    @Override
    protected void initView() {
        setTitle("发布");
        mEtTitle = findViewById(R.id.et_title);
        mEtDesc = findViewById(R.id.et_desc);
        mRvPic = findViewById(R.id.rv_pic);
        iv_thumbnail = findViewById(R.id.iv_thumbnail);
        final View video = findViewById(R.id.video);
        final String fileVideoPath = mPresenter.getFileVideoPath();
        if (TextUtils.isEmpty(fileVideoPath)) {
            video.setVisibility(View.GONE);
            mRvPic.setVisibility(View.VISIBLE);
        } else {
            mRvPic.setVisibility(View.GONE);
            ImageManager.getInstance().loadImage(mContext, fileVideoPath, iv_thumbnail);
        }
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
            mPresenter.addContent();
        });
        findViewById(R.id.iv_play).setOnClickListener(v -> onIvPlayClicked());
    }

    @Override
    protected void initData() {
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

    public void onIvPlayClicked() {
        final String filePath = mPresenter.getFileVideoPath();
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        Intent intent = new Intent(mContext, VideoPreviewActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, filePath);
        startActivity(intent);
    }


    private CircleProgressDialog mCircleProgressDialog;

    @Override
    public void setUploadProgress(int progress) {
        if (mCircleProgressDialog != null) {
            mCircleProgressDialog.setProgress(progress);
        }
    }

    @Override
    public void dissCircleProgressDialog() {
        if (mCircleProgressDialog != null && mCircleProgressDialog.isShowing()) {
            mCircleProgressDialog.dismiss();
        }
    }

    @Override
    public void showCircleProgressDialog() {
        if (mCircleProgressDialog == null) {
            mCircleProgressDialog = new CircleProgressDialog(mContext);
        }
        if (!mCircleProgressDialog.isShowing()) {
            mCircleProgressDialog.setProgress(0);
            mCircleProgressDialog.show();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mPresenter.submitCancel();
    }

    @Override
    protected void onDestroy() {
        if (mCircleProgressDialog != null && mCircleProgressDialog.isShowing()) {
            mCircleProgressDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public String getContentTitle() {
        return mEtTitle.getTextRight();
    }

    @Override
    public String getContents() {
        return mEtDesc.getText().toString();
    }
}
