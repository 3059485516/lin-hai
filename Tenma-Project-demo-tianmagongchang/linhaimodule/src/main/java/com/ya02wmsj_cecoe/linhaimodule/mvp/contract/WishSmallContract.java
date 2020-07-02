package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-08-14.
 */
public interface WishSmallContract {
    interface View extends IView {

    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void addTinyWish(Map<String, Object> map);

        public abstract List<LocalMedia> getImageList();
    }
}
