package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 我要秀文明
 */
public interface ShowCvilizePublishContract {
    interface View extends IView {
        void setUploadProgress(int progress);
        void showCircleProgressDialog();
        void dissCircleProgressDialog();
    }

    abstract class Presenter extends APresenter<View> {
        private List<LocalMedia> mImages = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void editContent(Map<String, Object> map, String videoPath);

        public List<LocalMedia> getImageList() {
            return mImages;
        }
    }
}
