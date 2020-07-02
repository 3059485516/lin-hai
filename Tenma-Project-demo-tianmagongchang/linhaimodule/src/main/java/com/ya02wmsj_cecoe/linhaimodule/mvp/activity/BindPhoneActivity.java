package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.widget.EditText;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.BindPhoneContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.BindPhonePresenter;


/**
 * Created by BenyChan on 2019-07-23.
 */
public class BindPhoneActivity extends BaseActivity<BindPhoneContract.Presenter> implements BindPhoneContract.View {
    protected EditText mEtPhone;

    protected EditText mEtCode;

    protected TextView mBtnCode;

    protected TextView mBtnBind;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_bind;
    }

    @Override
    protected void initMVP() {
        mPresenter = new BindPhonePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("绑定手机");
        mEtPhone = findViewById(R.id.tv_phone);
        mEtCode = findViewById(R.id.tv_code);
        mBtnCode = findViewById(R.id.tv_find_passwd);
        mBtnBind = findViewById(R.id.btn_register);
        mBtnCode.setOnClickListener(v -> {
            //  获取验证码
            if (mEtPhone.getText() != null) {
                mPresenter.getSMSCode(mEtPhone.getText().toString());
            }
        });
        mBtnBind.setOnClickListener(v -> {
            //  绑定
            if (mEtPhone.getText() != null && mEtCode.getText() != null) {
                mPresenter.bindPhone(mEtPhone.getText().toString(), mEtCode.getText().toString());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setCodeEnable(boolean enable) {
        mBtnCode.setEnabled(enable);
        if (enable) {
            mEtCode.setText("");
        }
    }

    @Override
    public void setCountDownText(String text) {
        mBtnCode.setText(text);
    }
}
