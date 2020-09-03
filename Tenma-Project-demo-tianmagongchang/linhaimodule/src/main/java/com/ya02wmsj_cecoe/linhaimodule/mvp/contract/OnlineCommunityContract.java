package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OnlineCommunity;

/**
 * 网络社区
 */
public interface OnlineCommunityContract {
    interface View extends IListView {
    }

    abstract class Presenter extends AListPresenter<View, OnlineCommunity> {
        public Presenter(View view) {
            super(view);
        }
        public abstract void setRegionCode(String region);
    }
}
