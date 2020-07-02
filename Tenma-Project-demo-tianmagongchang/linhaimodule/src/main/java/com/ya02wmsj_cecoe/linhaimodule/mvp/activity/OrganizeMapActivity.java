package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;

/**
 * Created by BenyChan on 2019-08-07.
 */
public class OrganizeMapActivity extends BaseActivity {
    protected MapView mMapView;

    private AMap aMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);  //该方法必须重写
        aMap = mMapView.getMap();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_three;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("组织结构");
        setMenuText("图表显示");
        mMapView = findViewById(R.id.map_view);
        findViewById(R.id.et_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(SearchOrganizeActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMenuClicked() {
        gotoActivity(OrganizeTreeActivity.class);
    }
}
