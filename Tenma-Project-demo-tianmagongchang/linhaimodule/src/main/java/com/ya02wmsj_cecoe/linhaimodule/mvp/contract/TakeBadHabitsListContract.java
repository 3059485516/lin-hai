package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/30 9:32 AM
 * desc   : EMPTY
 * ================================================
 */
public interface TakeBadHabitsListContract {
    interface View extends IListView {
    }

    abstract class Presenter extends AListPresenter<View, NodeContent> {
        public Presenter(View view) {
            super(view);
        }
    }
}
