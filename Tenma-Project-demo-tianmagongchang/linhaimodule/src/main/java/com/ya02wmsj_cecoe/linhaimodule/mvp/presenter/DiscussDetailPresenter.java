package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.DiscussDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;
import java.util.Map;

public class DiscussDetailPresenter extends DiscussDetailContract.Presenter {
    public DiscussDetailPresenter(DiscussDetailContract.View view) {
        super(view);
    }

    @Override
    public void getCommentByConId(String cId) {
        addRx2Destroy(new RxSubscriber<List<CommentEntity>>(Api.getCommentById(cId, "")) {
            @Override
            protected void _onNext(List<CommentEntity> list) {
                if (list != null) {
                    mCommentList.clear();
                    mCommentList.addAll(list);
                    mView.getCommentSuc();
                }
            }
        });
    }

    @Override
    public void getCommentByCommentId(String cId, String comId) {
        addRx2Destroy(new RxSubscriber<List<CommentEntity>>(Api.getCommentById(cId, comId)) {
            @Override
            protected void _onNext(List<CommentEntity> list) {
                if (list != null) {
                    mCommentSubList.clear();
                    mCommentSubList.addAll(list);
                    mView.getSubCommentSuc();
                }
            }
        });
    }

    @Override
    public void addDiscuss(Map<String, Object> map) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.operateDiscuss(map)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("发布成功，正在审核中..");
                mView.discussSuc();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
