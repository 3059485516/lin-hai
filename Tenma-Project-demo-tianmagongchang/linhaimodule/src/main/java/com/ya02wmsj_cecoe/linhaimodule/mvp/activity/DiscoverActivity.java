package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.DiscoverContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.DiscoverPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.CircleProgressDialog;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.Map;

public class DiscoverActivity extends BaseActivity<DiscoverPresenter> implements DiscoverContract.View {
    private YLEditTextGroup mEtTitle, mEtName, mEtPhone;
    private EditText mEtContent, mEtAddr;
    private RecyclerView mRvThumb, mRvPic;
    private ImageView mIvAddMedia, mIvPlay;
    private RatioImageView mIvThumbnail;
    private FrameLayout mWrapVideo;

    private ImageAdapter mThumbAdapter, mPicAdapter;
    private static int REQUEST_THUMB_CODE = 100;
    private static int REQUEST_IMAGE_CODE = 101;
    private static int REQUEST_VIDEO_CODE = 102;

    private String mVideoPath;

    private CircleProgressDialog mCircleProgressDialog;

    @Override
    protected int getLayoutId() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return R.layout.ya02wmsj_cecoe_discover_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new DiscoverPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("发现非遗");
        setMenuText("发布");

        mEtTitle = findViewById(R.id.et_title);
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_cancat);
        mEtContent = findViewById(R.id.et_content);
        mEtAddr = findViewById(R.id.et_addr);
        mRvThumb = findViewById(R.id.rv_thumb);
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

        mRvPic.setLayoutManager(new GridLayoutManager(this, 4));
        mPicAdapter = new ImageAdapter(this, mPresenter.getPicList());
        mPicAdapter.setMax(9);
        mRvPic.setAdapter(mPicAdapter);
        mPicAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int id = view.getId();
                if (R.id.iv_image == id) {
                    PictureSelectorUtils.getImageMultipleOption(mContext, mPresenter.getPicList(), 9, REQUEST_IMAGE_CODE);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRvThumb.setLayoutManager(new GridLayoutManager(this, 4));
        mThumbAdapter = new ImageAdapter(this, mPresenter.getThumbList());
        mThumbAdapter.setMax(1);
        mRvThumb.setAdapter(mThumbAdapter);
        mThumbAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int id = view.getId();
                if (R.id.iv_image == id) {
                    PictureSelectorUtils.getImageMultipleOption(mContext, mPresenter.getThumbList(), 1, REQUEST_THUMB_CODE);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mPresenter.publishCancel();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMenuClicked() {
        if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
            toast("请填写标题");
            return;
        }
        if (TextUtils.isEmpty(mEtName.getTextRight())) {
            toast("请填写联系人");
            return;
        }
        if (TextUtils.isEmpty(mEtPhone.getTextRight())) {
            toast("请填写联系方式");
            return;
        }
        if (TextUtils.isEmpty(mEtAddr.getText())) {
            toast("请填写联系地址");
            return;
        }
        if (TextUtils.isEmpty(mEtContent.getText()) && TextUtils.isEmpty(mVideoPath)) {
            toast("内容描述和视频不能同时为空");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("title", mEtTitle.getTextRight());
        map.put("contents", mEtContent.getText());
        map.put("name", mEtName.getTextRight());
        map.put("concat", mEtPhone.getTextRight());
        map.put("address", mEtAddr.getText());
        mPresenter.publish(map, mVideoPath);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_IMAGE_CODE) {
            mPresenter.getPicList().clear();
            mPresenter.getPicList().addAll(PictureSelector.obtainMultipleResult(data));
            mPicAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_THUMB_CODE) {
            mPresenter.getThumbList().clear();
            mPresenter.getThumbList().addAll(PictureSelector.obtainMultipleResult(data));
            mThumbAdapter.notifyDataSetChanged();
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
