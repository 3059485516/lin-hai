package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;

import java.util.ArrayList;
import java.util.List;

public interface AppreaceScoreContract {
    interface View extends IView {
        void updateComment();
    }

    abstract class Presenter extends APresenter<View> {
        private List<CommentEntity> mCommentList = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getComment(String c_id);

        public abstract void commit(String activityId, String voteIds, String scores);

        public abstract void addComment(String c_id, String content);

        public abstract void like(String c_id);

        public abstract void collect(String c_id);

        public List<CommentEntity> getCommentList() {
            return mCommentList;
        }
    }
}
