package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-26.
 */
public interface TheoryContract {
    interface View extends IListView {
        void updateActionList();
    }

    abstract class Presenter extends AListPresenter<View, NodeContent> {
        public Presenter(View view) {
            super(view);
        }

        public abstract List<NodeContent> getAdvanceTopList();

        public abstract void getAdvanceList();
    }
}
