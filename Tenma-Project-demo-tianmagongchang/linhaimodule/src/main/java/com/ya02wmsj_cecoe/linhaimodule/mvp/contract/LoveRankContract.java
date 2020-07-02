package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LoveRankEntity;

/**
 * Created by BenyChan on 2019-07-26.
 */
public interface LoveRankContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, LoveRankEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
