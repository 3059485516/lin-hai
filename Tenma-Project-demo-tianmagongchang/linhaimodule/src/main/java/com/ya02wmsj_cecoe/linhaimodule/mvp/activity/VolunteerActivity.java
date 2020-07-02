package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.text.TextUtils;
import android.widget.EditText;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.VolunteerContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.VolunteerPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;


/**
 * Created by BenyChan on 2019-07-23.
 */
public class VolunteerActivity extends BaseActivity<VolunteerContract.Presenter> implements VolunteerContract.View {
    protected YLEditTextGroup mEtName;

    protected EditText mEtAddress;

    protected EditText mEtIntro;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_volunteer;
    }

    @Override
    protected void initMVP() {
        mPresenter = new VolunteerPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("志愿者申请");
        setMenuText("提交");
        mEtName = findViewById(R.id.et_name);
        mEtAddress = findViewById(R.id.et_address);
        mEtIntro = findViewById(R.id.et_intro);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMenuClicked() {
        if (TextUtils.isEmpty(mEtName.getTextRight())) {
            toast("姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(mEtAddress.getText())) {
            toast("地址不能为空！");
            return;
        }
        if (TextUtils.isEmpty(mEtIntro.getText())) {
            toast("简介不能为空！");
            return;
        }
        mPresenter.applyForVolunteer(mEtName.getTextRight(), mEtAddress.getText().toString(), mEtIntro.getText().toString());
    }
}
