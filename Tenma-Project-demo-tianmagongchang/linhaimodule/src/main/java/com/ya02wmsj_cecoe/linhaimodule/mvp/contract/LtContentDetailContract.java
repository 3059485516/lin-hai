package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

import java.util.Map;

public interface LtContentDetailContract {
    interface View extends IView {
        void updateView(NodeContent nodeContent);

        void commentSuc();

        void loadCommentSuc();

        void likeSuc();

        void collectSuc();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getLtContentDetail(Map<String, Object> map);

        public abstract void addComment(String id, String content);

        public abstract void getLtCommentByConId(String c_id, int page);

        public abstract void like(String c_id);

        public abstract void collect(String c_id);

        public abstract void browse(String c_id);
    }
}
