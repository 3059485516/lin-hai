package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationSubEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrganizationDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrganizationDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;


/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizationDetailActivity extends BaseActivity<OrganizationDetailContract.Presenter> implements OrganizationDetailContract.View {
    protected MapView mMapView;

    protected TextView mTvName;

    protected YLTextViewGroup mTvAddress;

    protected YLTextViewGroup mTvPeople;

    protected YLTextViewGroup mTvPhone;

    protected YLTextViewGroup mTvCount;

    protected YLTextViewGroup mTvChild;

    private AMap aMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);  //该方法必须重写
        aMap = mMapView.getMap();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_actvity_organize_detail;
    }

    @Override
    protected void initMVP() {
        OrganizationSubEntity entity = (OrganizationSubEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mPresenter = new OrganizationDetailPresenter(this, entity.getType(), entity.getId());
    }

    @Override
    protected void initView() {
        setTitle("组织结构");
        mMapView = findViewById(R.id.map_view);
        mTvName = findViewById(R.id.tv_name);
        mTvAddress = findViewById(R.id.tv_addr);
        mTvPeople = findViewById(R.id.tv_people);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvCount = findViewById(R.id.tv_count);
        mTvChild = findViewById(R.id.tv_child);
    }

    @Override
    protected void initData() {
        mPresenter.getOrganDetail();
    }

    @Override
    public void updateView(OrganizationDetailEntity obj) {
        mTvName.setText(obj.getName());
        mTvAddress.setTextRight(obj.getAddress());
        mTvPeople.setTextRight(obj.getCharge_name());
        mTvCount.setTextRight(obj.getPoints());
        if (obj.getCultural_auditorium() != null || obj.getOrgan_practice() != null) {
            mTvChild.setOnClickListener(v -> {
                //  显示下属
                Intent intent = new Intent(mContext, OrganizationSubActivity.class);
                intent.putExtra(Constant.KEY_BEAN, obj);
                mContext.startActivity(intent);
            });
        } else {
            mTvChild.setOnClickListener(v -> {
                toast("暂无数据");
            });
        }
        if (!TextUtils.isEmpty(obj.getLatitude()) && !TextUtils.isEmpty(obj.getLongitude())) {
            try {
                double lat = Double.parseDouble(obj.getLatitude());
                double log = Double.parseDouble(obj.getLongitude());
//                double lat = 40.2436970000;     // 马德里
//                double log = -3.7118190000;
                LatLng last = new LatLng(lat, log);
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(last, 12));    // 设置当前位置
                Marker marker = aMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, log))
                        .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(getResources(), R.mipmap.ya02wmsj_cecoe_map_mark)))
                        .draggable(false)
                        .title(obj.getName()));

            } catch (NumberFormatException e) {
                toast("经纬度异常");
            }
        }
    }
}
