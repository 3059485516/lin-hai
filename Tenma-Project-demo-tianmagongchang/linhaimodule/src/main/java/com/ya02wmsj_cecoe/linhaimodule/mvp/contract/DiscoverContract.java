package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;


import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import java.util.Map;

/**
 * @author Yang Shihao
 * 上传点播视频
 */
public interface DiscoverContract {
    interface View extends IView {
        void setUploadProgress(int progress);

        void showCircleProgressDialog();

        void dissCircleProgressDialog();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void publish(Map<String, Object> map, String videoPath);

        public abstract void publishCancel();
    }
}
