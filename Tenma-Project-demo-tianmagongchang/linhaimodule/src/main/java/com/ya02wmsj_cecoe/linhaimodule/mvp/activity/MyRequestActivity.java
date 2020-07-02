package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;


/**
 * Created by BenyChan on 2019-08-13.
 */
public class MyRequestActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_my_request;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("我求");
        findViewById(R.id.my_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 微心愿页面
                gotoActivity(WishSmallActivity.class);
            }
        });

        findViewById(R.id.do_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 诉求页面
                gotoActivity(AppealCommitActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
