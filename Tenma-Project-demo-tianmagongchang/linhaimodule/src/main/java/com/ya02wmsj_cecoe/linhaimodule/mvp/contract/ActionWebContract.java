package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import java.util.Map;

/**
 * Created by BenyChan on 2019-06-20.
 */
public interface ActionWebContract {
    interface View extends IView {
        void updateVoteCount(int position);
        void updateInfo(AppraiseEntity appraiseEntity);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }
        public abstract void vote(int position, Map<String, Object> map);
        public abstract void getOnlineActivityDetail(String id);
    }
}
