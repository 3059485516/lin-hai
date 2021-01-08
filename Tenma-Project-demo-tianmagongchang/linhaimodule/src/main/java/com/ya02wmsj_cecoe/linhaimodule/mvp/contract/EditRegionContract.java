package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-27.
 */
public interface EditRegionContract {
    interface View extends IView {
        void updateRegion1(RegionEntity entity);
        void updateRegion2(List<RegionEntity> list);
        void updateRegion3(List<RegionEntity> list);
        void updateSelectRegion();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void bindArea(String region_code);

        public abstract void getRegionData1();

        public abstract void getRegionData2(String type, String pid);

        public abstract void getRegionData3(String type, String pid);
    }
}
