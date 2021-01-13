package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ShowCvilizePublishContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ShowCvilizePublishPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.CircleProgressDialog;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 我要秀文明的发布页面
 */
public class ShowCvilizePublishActivity extends BaseActivity<ShowCvilizePublishContract.Presenter> implements ShowCvilizePublishContract.View {
    private YLEditTextGroup mEtTitle;
    private EditText mEtContent;
    private RecyclerView mRvPic;
    private ImageView mIvAddMedia, mIvPlay;
    private RatioImageView mIvThumbnail;
    private FrameLayout mWrapVideo;
    protected ImageAdapter mImgAdapter;
    private static int REQUEST_VIDEO_CODE = 110;

    private String mVideoPath;

    private CircleProgressDialog mCircleProgressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_show_civilize_publish;
    }

    @Override
    protected void initMVP() {
        mPresenter = new ShowCvilizePublishPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("内容发布");
        setMenuText("我发布的");
        mEtTitle = findViewById(R.id.et_title);
        mEtContent = findViewById(R.id.et_content);
        mRvPic = findViewById(R.id.rv_pic);
        mIvAddMedia = findViewById(R.id.iv_add_video);
        mIvPlay = findViewById(R.id.iv_play);
        mIvThumbnail = findViewById(R.id.iv_thumbnail);
        mWrapVideo = findViewById(R.id.wrap_video);
        mWrapVideo.setVisibility(View.GONE);
        mIvAddMedia.setVisibility(View.VISIBLE);
        mIvAddMedia.setOnClickListener(v -> startActivityForResult(new Intent(mContext, SelectVideoActivity.class), REQUEST_VIDEO_CODE));
        mIvPlay.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mVideoPath)) {
                return;
            }
            Intent intent = new Intent(mContext, VideoPreviewActivity.class);
            intent.putExtra(Constant.KEY_STRING_1, mVideoPath);
            startActivity(intent);
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

        findViewById(R.id.btn_commit).setOnClickListener(v -> {
            if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
                toast("请输入标题");
                return;
            }
            if (TextUtils.isEmpty(mEtContent.getText())) {
                toast("请输入内容");
                return;
            }

            Map<String, Object> map = new HashMap<>();
            map.put("show_type", "内容");
            map.put("node_id", "30");
            map.put("title", mEtTitle.getTextRight());
            map.put("contents", mEtContent.getText());
            mPresenter.editContent(map, mVideoPath);
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
        } else if (requestCode == REQUEST_VIDEO_CODE) {
            mVideoPath = data.getStringExtra(Constant.KEY_STRING_1);
            if (!TextUtils.isEmpty(mVideoPath)) {
                ImageManager.getInstance().loadImage(this, mVideoPath, mIvThumbnail);
                mIvAddMedia.setVisibility(View.GONE);
                mWrapVideo.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setUploadProgress(int progress) {
        if (mCircleProgressDialog != null) {
            mCircleProgressDialog.setProgress(progress);
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
    public void dissCircleProgressDialog() {
        if (mCircleProgressDialog != null && mCircleProgressDialog.isShowing()) {
            mCircleProgressDialog.dismiss();
        }
    }
}
