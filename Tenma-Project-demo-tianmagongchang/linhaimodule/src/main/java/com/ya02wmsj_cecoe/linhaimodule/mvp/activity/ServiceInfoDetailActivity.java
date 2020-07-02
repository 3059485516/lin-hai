package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ServiceConcatAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceInfoEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.HtmlUtil;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;


public class ServiceInfoDetailActivity extends BaseActivity {
    protected MapView mMapView;

    TextView mTvTitle;

    TextView mTvDesc;

    TextView mTvServiceTime;

    TextView mTvAddr;

    RecyclerView mRvPerson;

    private AMap aMap;
    private ServiceInfoEntity mEntity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEntity = (ServiceInfoEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);

        mMapView = findViewById(R.id.map_view);
        mTvTitle = findViewById(R.id.tv_title);
        mTvDesc = findViewById(R.id.tv_desc);
        mTvServiceTime = findViewById(R.id.tv_service_time);
        mTvAddr = findViewById(R.id.tv_addr);
        mRvPerson = findViewById(R.id.rv_person);

        mMapView.onCreate(savedInstanceState);  //该方法必须重写
        aMap = mMapView.getMap();

        setTitle(mEntity.getCategory_name());
        mTvTitle.setText(mEntity.getTitle());
        mTvDesc.setText(HtmlUtil.getTextFromHtml(mEntity.getDesc()));
        mTvServiceTime.setText(mEntity.getServe_time());
        mTvAddr.setText(mEntity.getServe_address());
        if (!TextUtils.isEmpty(mEntity.getSt_lnglat())) {
            try {
                String[] location = mEntity.getSt_lnglat().split(",");
                double lat = Double.parseDouble(location[1]);
                double log = Double.parseDouble(location[0]);
//                double lat = 40.2436970000;     // 马德里
//                double log = -3.7118190000;
                LatLng last = new LatLng(lat, log);
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(last, 12));    // 设置当前位置
                Marker marker = aMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, log))
                        .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(getResources(), R.mipmap.ya02wmsj_cecoe_location3)))
                        .draggable(false)
                        .title(mEntity.getTitle()));

            } catch (Exception e) {
                toast("经纬度异常");
            }
        }

        if (mEntity.getServe_concat() != null) {
            mRvPerson.setLayoutManager(new LinearLayoutManager(this));
            int dimension = (int) getResources().getDimension(R.dimen.yl_list_horizontal_margin);
            mRvPerson.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, dimension, dimension));
            ServiceConcatAdapter adapter = new ServiceConcatAdapter(this, mEntity.getServe_concat());
            mRvPerson.setAdapter(adapter);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_service_detail;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
