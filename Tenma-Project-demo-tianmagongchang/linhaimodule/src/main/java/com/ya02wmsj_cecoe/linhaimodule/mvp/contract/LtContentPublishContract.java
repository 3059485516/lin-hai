package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LtContentPublishContract {
    interface View extends IView {
        void updateMark();

        void setUploadProgress(int progress);

        void showCircleProgressDialog();

        void dissCircleProgressDialog();
    }

    abstract class Presenter extends APresenter<View> {
        private List<LtMarkEntity> mMarkList = new ArrayList<>();
        private List<LocalMedia> mImages = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getMark();

        public abstract void editContent(Map<String, Object> map, String videoPath);

        public List<LtMarkEntity> getMarkList() {
            return mMarkList;
        }

        public List<LocalMedia> getImageList() {
            return mImages;
        }

        public abstract void publishCancel();
    }
}
