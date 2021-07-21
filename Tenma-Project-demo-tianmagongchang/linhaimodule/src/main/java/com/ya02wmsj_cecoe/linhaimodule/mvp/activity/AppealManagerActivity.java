package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;

/**
 * Created by BenyChan on 2019-08-07.
 */
public class AppealManagerActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_appeal_manager;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        setTitle("我诉");
        findViewById(R.id.my_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AppealActivity.class);
            }
        });

        findViewById(R.id.do_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 网络评议页面
                gotoActivity(PublishAppraiseActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
