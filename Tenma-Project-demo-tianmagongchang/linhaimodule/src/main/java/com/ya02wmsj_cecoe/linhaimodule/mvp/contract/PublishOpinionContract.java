package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import android.content.Intent;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-22.
 */
public interface PublishOpinionContract {
    interface View extends IView {

        void dissCircleProgressDialog();

        void setUploadProgress(int progress);

        String getContentTitle();

        void showCircleProgressDialog();
    }

    abstract class Presenter extends APresenter<View> {
        private final String fileVideoPath;
        private final String node_id;
        private final String show_type;
        public Presenter(View view, Intent intent) {
            super(view);
            node_id = intent.getStringExtra(Constant.KEY_STRING_1);
            show_type = intent.getStringExtra(Constant.KEY_STRING_2);
            fileVideoPath = intent.getStringExtra(Constant.KEY_STRING_3);
        }

        public String getFileVideoPath() {
            return fileVideoPath;
        }

        public String getNode_id() {
            return node_id;
        }

        public String getShow_type() {
            return show_type;
        }

        public abstract List<LocalMedia> getImageList();

        public abstract void commit(Map<String, Object> map);
    }
}
