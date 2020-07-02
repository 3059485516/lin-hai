package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;

public class LtResourceAppointActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_lt_resource_appoint;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("资源预约");
        setMenuText("我的预约");
        findViewById(R.id.wrap1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  礼堂点单
                mContext.startActivity(new Intent(mContext, OrderBetActivity.class));
            }
        });

        findViewById(R.id.wrap2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  礼堂资源
                mContext.startActivity(new Intent(mContext, LtChooseLtActivity.class));
            }
        });
    }

    @Override
    public void onMenuClicked() {
        startActivity(new Intent(this, LtMyAppointActivity.class));
    }

    @Override
    protected void initData() {

    }
}
