package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TextContentContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-25.
 */
public class TextContentPresenter extends TextContentContract.Presenter {
    private List<CommentEntity> mCommentList = new ArrayList<>();

    public TextContentPresenter(TextContentContract.View view) {
        super(view);
    }

    @Override
    public List<CommentEntity> getCommentList() {
        return mCommentList;
    }

    @Override
    public void getCommentById(String c_id) {
        addRx2Destroy(new RxSubscriber<List<CommentEntity>>(Api.getCommentById(c_id, "")) {
            @Override
            protected void _onNext(List<CommentEntity> list) {
                if (list != null) {
                    mCommentList.clear();
                    mCommentList.addAll(list);
                    mView.updateComment();
                }
            }
        });
    }

    @Override
    public void addComment(String c_id, String content) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<String>(Api.addComment(c_id, content)) {
            @Override
            protected void _onNext(String count) {
                mView.dismissDialog();
                toast("评论成功");
                mView.addCommentSuc(count);
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }

    @Override
    public void like(String c_id) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.likeContent(c_id)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                mView.likeSuc();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }

    @Override
    public void collect(String c_id) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.collectContent(c_id)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                mView.collectSuc();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }

    @Override
    public void browse(String c_id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.browseContent(c_id)) {
            @Override
            protected void _onNext(Object o) {
            }

            @Override
            protected void _onError(String code) {
            }
        });
    }

    @Override
    public void getContentDetail(Map<String, Object> map) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getContentDetail(map)) {
            @Override
            protected void _onNext(List<NodeContent> list) {
                mView.dismissDialog();
                if (list != null && list.size() > 0) {
                    mView.updateView(list.get(0));
                }
            }
            protected  void _onError(String code){
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
