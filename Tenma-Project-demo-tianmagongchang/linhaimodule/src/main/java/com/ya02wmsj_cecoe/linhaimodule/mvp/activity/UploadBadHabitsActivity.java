package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.UploadBadHabitsContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.UploadBadHabitsPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.CircleProgressDialog;

public class UploadBadHabitsActivity extends BaseActivity<UploadBadHabitsContract.Presenter>implements UploadBadHabitsContract.View {

    private CircleProgressDialog mCircleProgressDialog;

    public static void launch(Context context, String filePath){
        context.startActivity(new Intent(context,UploadBadHabitsActivity.class)
        .putExtra(Constant.KEY_STRING_1,filePath));
    }
    protected EditText mEtDesc;

    protected EditText mEtTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_upload_bad_habits;
    }

    @Override
    protected void initMVP() {
        mPresenter = new UploadBadHabitsPresenter(this,getIntent());
    }

    @Override
    protected void initView() {
        mEtDesc = findViewById(R.id.et_desc);
        mEtTitle = findViewById(R.id.et_title);
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIvPlayClicked();
            }
        };
        findViewById(R.id.iv_play).setOnClickListener(onClickListener);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setUploadProgress(int progress) {
        if (mCircleProgressDialog != null){
            mCircleProgressDialog.setProgress(progress);
        }
    }

    @Override
    public void dissCircleProgressDialog() {
        if (mCircleProgressDialog != null && mCircleProgressDialog.isShowing()){
            mCircleProgressDialog.dismiss();
        }
    }

    @Override
    public String getVideoTitle() {
        return mEtTitle.getText().toString();
    }

    @Override
    public String getContent() {
        return mEtDesc.getText().toString();
    }

    @Override
    public void showCircleProgressDialog() {
        if (mCircleProgressDialog == null){
            mCircleProgressDialog = new CircleProgressDialog(mContext);
        }
        if (!mCircleProgressDialog.isShowing()){
            mCircleProgressDialog.setProgress(0);
            mCircleProgressDialog.show();
        }
    }

    public void onIvPlayClicked() {
        final String filePath = mPresenter.getFilePath();
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        Intent intent = new Intent(mContext, VideoPreviewActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, filePath);
        startActivity(intent);
    }
}