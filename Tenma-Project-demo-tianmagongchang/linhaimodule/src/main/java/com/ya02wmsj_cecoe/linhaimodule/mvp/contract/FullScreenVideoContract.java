package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FullScreenVideoContract {
    interface View extends IView {
        void updateView(Object obj);

        void likeSuc();

        void collectSuc();

        void shareSuc();

        void commentSuc(String count);

        void updateComment();
    }

    abstract class Presenter extends APresenter<View> {
        private List<CommentEntity> mCommentList = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getDetailContent(Map<String, Object> map);

        public abstract void like(String id);

        public abstract void collect(String id);

        public abstract void share(String id);

        public abstract void getCommentById(String c_id);

        public abstract void addComment(String id, String content);

        public List<CommentEntity> getCommentList() {
            return mCommentList;
        }
    }
}
