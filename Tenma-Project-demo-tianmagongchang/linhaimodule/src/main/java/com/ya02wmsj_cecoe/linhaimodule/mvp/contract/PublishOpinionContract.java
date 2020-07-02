package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-22.
 */
public interface PublishOpinionContract {
    interface View extends IView {

    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract List<LocalMedia> getImageList();

        public abstract void commit(Map<String, Object> map);
    }
}
