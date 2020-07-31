package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.adapter.TakeBadHabitsAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.BaseCleanListContract;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.BaseQuickAdapterPresenter;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/30 9:32 AM
 * desc   : EMPTY
 * ================================================
 */
public interface TakeBadHabitsListContract {
    interface View extends BaseCleanListContract.View{

    }

    abstract class Presenter extends BaseQuickAdapterPresenter<TakeBadHabitsAdapter,View, NodeContent>{

        public Presenter(View view) {
            super(view);
            mAdapter = new TakeBadHabitsAdapter(mData);
        }
    }
}
