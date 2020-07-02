package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-16.
 */
public interface BaseAreaFragmentContract {
    interface View extends IView {
        void updateContry();

        void updateTown();

        void updateVillage();

        void updateOnlineList();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getRegionCountyData();

        public abstract void getRegionTownData();

        public abstract void getRegionVillageData();

        public abstract void getOnlineActivityList();

        public abstract List<AppraiseEntity> getOnlineList();
    }
}
