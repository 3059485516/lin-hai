package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppealContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppealPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-27.
 */
public class AppealActivity extends BaseActivity<AppealContract.Presenter> implements AppealContract.View {
    protected YLEditTextGroup mEtTitle;
    protected EditText mEtDesc;
    protected YLTextViewGroup mTvVideo;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_appeal;
    }

    @Override
    protected void initMVP() {
        mPresenter = new AppealPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("爆料");
        mEtTitle = findViewById(R.id.et_title);
        mEtDesc = findViewById(R.id.et_desc);
        mTvVideo = findViewById(R.id.tv_video);
        mTvVideo.setOnClickListener(v -> {
            PictureSelectorUtils.getVideoSingleOption(mContext, PictureConfig.TYPE_VIDEO);
        });
        findViewById(R.id.btn_commit).setOnClickListener(v -> {
            if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
                toast("请填写标题");
                return;
            }
            if (TextUtils.isEmpty(mEtDesc.getText())) {
                toast("请填写描述");
                return;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("show_type", "爆料");
            map.put("title", mEtTitle.getTextRight());
            map.put("contents", mEtDesc.getText().toString());
            mPresenter.addContentAppeal(map);
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PictureConfig.TYPE_VIDEO) {
            List<LocalMedia> videoList = PictureSelector.obtainMultipleResult(data);
            LocalMedia media = videoList.get(0);
            String path;
            if (!TextUtils.isEmpty(media.getCompressPath())) {
                path = media.getCompressPath();
            } else {
                path = media.getPath();
            }
            mTvVideo.setTextRight(path);
            mTvVideo.setTag(path);
        }
    }
}
