package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtContentDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LtContentDetailPresenter extends LtContentDetailContract.Presenter {
    private List<CommentEntity> mCommentList = new ArrayList<>();

    public LtContentDetailPresenter(LtContentDetailContract.View view) {
        super(view);
    }

    public List<CommentEntity> getCommentList() {
        return mCommentList;
    }

    @Override
    public void getLtContentDetail(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getLtContentDetail(map)) {
            @Override
            protected void _onNext(List<NodeContent> list) {
                if (list != null && list.size() > 0) {
                    if (list.get(0) != null) {
                        mView.updateView(list.get(0));
                    }
                }
            }
        });
    }

    @Override
    public void addComment(String id, String content) {
        addRx2Destroy(new RxSubscriber<Object>(Api.ltAddComment(id, "0", content)) {
            @Override
            protected void _onNext(Object o) {
                toast("评论成功");
                mView.commentSuc();
            }
        });
    }

    @Override
    public void getLtCommentByConId(String c_id, int page) {
        addRx2Destroy(new RxSubscriber<List<CommentEntity>>(Api.getLtCommentByConId(c_id, page + "", "20")) {
            @Override
            protected void _onNext(List<CommentEntity> list) {
                if (list != null && list.size() > 0) {
                    mCommentList.addAll(list);
                    mView.loadCommentSuc();
                }
            }
        });
    }

    @Override
    public void like(String c_id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.conPraise(c_id)) {
            @Override
            protected void _onNext(Object o) {
                mView.likeSuc();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }

    @Override
    public void collect(String c_id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.conCollect(c_id)) {
            @Override
            protected void _onNext(Object o) {
                mView.collectSuc();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }

    @Override
    public void browse(String c_id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.recordUserBrowsing(c_id)) {
            @Override
            protected void _onNext(Object o) {

            }

            @Override
            protected void _onError(String code) {

            }
        });
    }
}
