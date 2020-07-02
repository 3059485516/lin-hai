package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;


/**
 * Created by BenyChan on 2019-07-09.
 */
public class TestActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_test_layout;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.tv_tv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Constant.getTMBasePackageName() + ".usercenter.login");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_tv_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Constant.getTMBasePackageName() + ".usercenter.UCMain");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_tv_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Constant.getTMBasePackageName() + ".usercenter.UCPersonal");
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_tv_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Constant.getTMBasePackageName() + ".usercenter.bindingMobile");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {

    }
}
