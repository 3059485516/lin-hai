package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtPublishMarkAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtContentPublishContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtContentPublishPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.CircleProgressDialog;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LtContentPublishActivity extends BaseActivity<LtContentPublishPresenter> implements LtContentPublishContract.View {
    private YLEditTextGroup mEtTitle;
    private EditText mEtContent;
    private RecyclerView mRvMark, mRvPic;
    private ImageView mIvAddMedia, mIvPlay;
    private RatioImageView mIvThumbnail;
    private FrameLayout mWrapVideo;

    protected ImageAdapter mImgAdapter;
    protected LtPublishMarkAdapter mMarkAdapter;

    private static int REQUEST_VIDEO_CODE = 110;

    private String mVideoPath;

    private StringBuilder mMarkId = new StringBuilder();
    private CircleProgressDialog mCircleProgressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_content_publish;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtContentPublishPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("内容发布");
        setMenuText("我发布的");
        mEtTitle = findViewById(R.id.et_title);
        mEtContent = findViewById(R.id.et_content);
        mRvMark = findViewById(R.id.rv_mark);
        mRvPic = findViewById(R.id.rv_pic);
        mIvAddMedia = findViewById(R.id.iv_add_video);
        mIvPlay = findViewById(R.id.iv_play);
        mIvThumbnail = findViewById(R.id.iv_thumbnail);
        mWrapVideo = findViewById(R.id.wrap_video);
        mWrapVideo.setVisibility(View.GONE);
        mIvAddMedia.setVisibility(View.VISIBLE);
        mIvAddMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(mContext, SelectVideoActivity.class), REQUEST_VIDEO_CODE);
            }
        });
        mIvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mVideoPath)) {
                    return;
                }
                Intent intent = new Intent(mContext, VideoPreviewActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, mVideoPath);
                startActivity(intent);
            }
        });
        mRvMark.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRvPic.setLayoutManager(new GridLayoutManager(this, 4));

        mMarkAdapter = new LtPublishMarkAdapter(this, mPresenter.getMarkList(), new LtPublishMarkAdapter.IMarkSelectedCallback() {
            @Override
            public void getSelectedList(List<LtMarkEntity> list) {
                mMarkId.delete(0, mMarkId.length());
                for (LtMarkEntity aList : list) {
                    mMarkId.append(aList.getId()).append(",");
                }
            }
        });
        mRvMark.setAdapter(mMarkAdapter);

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
                    toast("请输入标题");
                    return;
                }
                if (TextUtils.isEmpty(mEtContent.getText())) {
                    toast("请输入内容");
                    return;
                }
                if (TextUtils.isEmpty(mMarkId)) {
                    toast("请选择标签");
                    return;
                }

                Map<String, Object> map = new HashMap<>();
                map.put("id", "");
                map.put("title", mEtTitle.getTextRight());
                map.put("marks", mMarkId);
                map.put("contents", mEtContent.getText());
                mPresenter.editContent(map, mVideoPath);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getMark();
    }

    @Override
    public void onMenuClicked() {
        //  我发布的
        gotoActivity(LtMyPublishActivity.class);
    }

    @Override
    public void updateMark() {
        mMarkAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
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
