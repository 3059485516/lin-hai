package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LtEvaluationPublishContract {
    interface View extends IView {
        void showRulesDialog();
    }

    abstract class Presenter extends APresenter<View> {
        private List<LocalMedia> mImages = new ArrayList<>();
        private List<LtEvaEntity> mRules = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void caEvaApply(Map<String, Object> map);

        public abstract void getEvaRules();

        public List<LocalMedia> getImageList() {
            return mImages;
        }

        public List<LtEvaEntity> getRulesList() {
            return mRules;
        }
    }
}
