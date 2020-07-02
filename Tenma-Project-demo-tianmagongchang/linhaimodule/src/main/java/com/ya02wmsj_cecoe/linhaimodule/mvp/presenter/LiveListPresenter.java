package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LiveListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveRecordListActivity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class LiveListPresenter extends AListPresenter<IListView, LiveListEntity> {
    private LiveListEntity mEntity;

    public LiveListPresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getLiveList());
    }

    public void getLiveStatus(LiveListEntity entity) {
        mEntity = entity;
        addRx2Destroy(new RxSubscriber<String>(Api.getLiveStatus(entity.getCid())) {
            @Override
            protected void _onNext(String s) {
                if (!TextUtils.isEmpty(s)) {
                    if ("空闲".equals(s)) {
                        mView.toast("直播空闲中");
                        Intent intent = new Intent(mContext, LiveRecordListActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, entity.getCid());
                        mView.gotoActivity(intent);
                    } else if ("直播".equals(s)) {
                        Intent intent = new Intent(mContext, LiveActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, mEntity.getName());
                        intent.putExtra(Constant.KEY_STRING_2, mEntity.getHls_pull_url());
                        mView.gotoActivity(intent);
                    }
                }
            }
        });
    }
}
