package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.BaseAreaListFragmentPresenter;

/**
 * 文化礼堂
 * Created by BenyChan on 2019-07-23.
 */
public interface LTFragmentContract {
    interface View extends BaseAreaListFragmentContract.View {
    }

    abstract class Presenter extends BaseAreaListFragmentPresenter {
        public Presenter(View view) {
            super(view);
        }
    }
}
