package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OtherServicesContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OtherServicesPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

/**
 * 我要点服务 --- 其他服务
 * Created by BenyChan on 2019-07-23.
 */
public class OtherServicesActivity extends BaseActivity<OtherServicesContract.Presenter> implements OtherServicesContract.View {
    protected Button btnCommit;
    protected YLEditTextGroup etYLName;
    protected YLEditTextGroup etYLPhone;
    protected YLTextViewGroup tvYLRegion;
    protected YLEditTextGroup etYLAddress;
    protected EditText etDesc;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_other_services;
    }

    @Override
    protected void initMVP() {
        mPresenter = new OtherServicesPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("其他服务");
        btnCommit = findViewById(R.id.btn_commit);
        etYLName = findViewById(R.id.et_name);
        etYLPhone = findViewById(R.id.et_phone);
        tvYLRegion = findViewById(R.id.tv_region);
        etYLAddress = findViewById(R.id.et_address);
        etDesc = findViewById(R.id.et_desc);

        btnCommit.setOnClickListener(v -> mPresenter.submitData());
        tvYLRegion.setOnClickListener(v -> startActivityForResult(new Intent(mContext, SelectRegionActivity.class), REQUEST_REGION_CODE));
    }

    @Override
    protected void initData() {
    }

    public static final int REQUEST_REGION_CODE = 400;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_REGION_CODE) {
            tvYLRegion.setTextRight(data.getStringExtra(Constant.KEY_STRING_1));
            tvYLRegion.setTag(data.getStringExtra(Constant.KEY_STRING_2));
        }
    }

    @Override
    public String getName() {
        return etYLName.getTextRight();
    }

    @Override
    public String getPhone() {
        return etYLPhone.getTextRight();
    }

    @Override
    public String getRegionCode() {
        Object object = tvYLRegion.getTag();
        String result = "";
        if (object != null) {
            result = (String) object;
        }
        return result;
    }

    @Override
    public String getAddress() {
        return etYLAddress.getTextRight();
    }

    @Override
    public String getContent() {
        return etDesc.getText().toString();
    }
}
