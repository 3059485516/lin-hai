package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppreaceScoreContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

public class AppreaceScorePresenter extends AppreaceScoreContract.Presenter {
    public AppreaceScorePresenter(AppreaceScoreContract.View view) {
        super(view);
    }

    @Override
    public void getComment(String c_id) {
        addRx2Destroy(new RxSubscriber<List<CommentEntity>>(Api.getCommentById(c_id, "")) {
            @Override
            protected void _onNext(List<CommentEntity> list) {
                if (list != null) {
                    getCommentList().clear();
                    getCommentList().addAll(list);
                    mView.updateComment();
                }
            }
        });
    }

    @Override
    public void commit(String activityId, String scoreIds, String optionIds, String scores) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.giveScore(activityId, scoreIds, optionIds, scores),mView) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
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
                toast("点赞成功");
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
                toast("收藏成功");
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
