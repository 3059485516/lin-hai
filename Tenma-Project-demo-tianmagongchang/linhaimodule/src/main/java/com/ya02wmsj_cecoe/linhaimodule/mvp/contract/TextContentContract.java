package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-25.
 */
public interface TextContentContract {
    interface View extends IView {
        void updateComment();

        void addCommentSuc(String count);

        void likeSuc();

        void collectSuc();

        void updateView(NodeContent nodeContent);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract List<CommentEntity> getCommentList();

        public abstract void getCommentById(String c_id);

        public abstract void addComment(String c_id, String content);

        public abstract void like(String c_id);

        public abstract void collect(String c_id);

        public abstract void browse(String c_id);

        public abstract void getContentDetail(Map<String, Object> map);
    }
}
