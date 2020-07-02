package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;

import java.util.List;
import java.util.Map;

public interface SocialCollectContract {
    interface View extends IView {

        void updateRegionContry(RegionEntity entity);

        void updateTown(List<RegionEntity> list);

        void updateVillage(List<RegionEntity> list);

        void uploadComplete();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract List<LocalMedia> getImageList();

        public abstract void getRegionDataCountry();

        public abstract void getRegionDataTown(String type, String pid);

        public abstract void getRegionDataVillage(String type, String pid);

        public abstract void collectNetInfo(Map<String, Object> map);
    }
}
