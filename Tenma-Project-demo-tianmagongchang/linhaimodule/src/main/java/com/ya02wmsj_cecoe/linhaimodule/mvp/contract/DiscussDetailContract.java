package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DiscussDetailContract {
    interface View extends IView {
        void getCommentSuc();

        void getSubCommentSuc();

        void discussSuc();
    }

    abstract class Presenter extends APresenter<View> {
        protected List<CommentEntity> mCommentList = new ArrayList<>();
        protected List<CommentEntity> mCommentSubList = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getCommentByConId(String cId);

        public abstract void getCommentByCommentId(String cId, String comId);

        public abstract void addDiscuss(Map<String, Object> map);

        public List<CommentEntity> getComnentList() {
            return mCommentList;
        }

        public List<CommentEntity> getComnentSubList() {
            return mCommentSubList;
        }
    }
}
