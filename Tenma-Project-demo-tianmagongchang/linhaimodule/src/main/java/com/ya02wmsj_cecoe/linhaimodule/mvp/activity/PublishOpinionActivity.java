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
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.PublishOpinionContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.PublishOpinionPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.Map;

public class PublishOpinionActivity extends BaseActivity<PublishOpinionContract.Presenter> implements PublishOpinionContract.View {
    protected YLEditTextGroup mEtTitle;

    protected EditText mEtDesc;

    protected RecyclerView mRvPic;

    protected ImageAdapter mImgAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_opinion;
    }

    @Override
    protected void initMVP() {
        mPresenter = new PublishOpinionPresenter(this,getIntent());
    }

    @Override
    protected void initView() {
        setTitle("发布");
        mEtTitle = findViewById(R.id.et_title);
        mEtDesc = findViewById(R.id.et_desc);
        mRvPic = findViewById(R.id.rv_pic);
        final View video = findViewById(R.id.video);
        final String fileVideoPath = mPresenter.getFileVideoPath();
        if(TextUtils.isEmpty(fileVideoPath)){
            video.setVisibility(View.GONE);
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
            Map<String, Object> params = new HashMap<>();
            params.put("node_id", mPresenter.getNode_id());
            params.put("show_type", mPresenter.getShow_type());
            params.put("title", mEtTitle.getTextRight());
            params.put("contents", mEtDesc.getText().toString());
//            params.put("video_path",mPresenter.getFileVideoPath());
            mPresenter.commit(params);
        });
        findViewById(R.id.iv_play).setOnClickListener(v -> onIvPlayClicked());
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

    public void onIvPlayClicked() {
        final String filePath = mPresenter.getFileVideoPath();
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        Intent intent = new Intent(mContext, VideoPreviewActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, filePath);
        startActivity(intent);
    }

    @Override
    public void dissCircleProgressDialog() {
        // TODO: 2020/8/5
    }

    @Override
    public void setUploadProgress(int progress) {
        // TODO: 2020/8/5
    }

    @Override
    public String getContentTitle() {
        // TODO: 2020/8/5
        return null;
    }

    @Override
    public void showCircleProgressDialog() {
        // TODO: 2020/8/5
    }
}
