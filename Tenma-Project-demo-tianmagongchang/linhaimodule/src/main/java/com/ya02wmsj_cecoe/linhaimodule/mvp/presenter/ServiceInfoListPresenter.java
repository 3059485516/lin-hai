package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.app.Activity;
import android.location.Location;
import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceInfoEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.utils.LocationUtils;

public class ServiceInfoListPresenter extends AListPresenter<IListView, ServiceInfoEntity> {
    private String mServiceId;
    private String mLongitude, mLatitude;

    public ServiceInfoListPresenter(IListView view, String id, Activity context) {
        super(view);
        mContext = context;
        mServiceId = id;
        mView.showDialog("正在获取当前位置");
        Location location = LocationUtils.getInstance(mContext).showLocation();
        if (location != null) {
            mLongitude = location.getLongitude() + "";
            mLatitude = location.getLatitude() + "";
            getPageData(true);
        }
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        if (TextUtils.isEmpty(mLongitude) || TextUtils.isEmpty(mLatitude)) {
            return;
        }
        setDataSource(Api.getServeInfoList(mServiceId, getPage() + "", PAGE_SIZE + "", mLongitude, mLatitude));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance(mContext).removeLocationUpdatesListener();
    }
}
