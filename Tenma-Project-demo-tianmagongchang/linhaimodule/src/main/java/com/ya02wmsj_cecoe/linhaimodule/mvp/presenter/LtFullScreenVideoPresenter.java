package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FullScreenVideoContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;
import java.util.Map;

public class LtFullScreenVideoPresenter extends FullScreenVideoContract.Presenter {

    public LtFullScreenVideoPresenter(FullScreenVideoContract.View view) {
        super(view);
    }

    @Override
    public void getDetailContent(Map<String, Object> map) {
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
    public void like(String id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.conPraise(id)) {
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
    public void collect(String id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.conCollect(id)) {
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
    public void share(String id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.conShare(id)) {
            @Override
            protected void _onNext(Object o) {
                mView.shareSuc();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }

    @Override
    public void getCommentById(String c_id) {
        addRx2Destroy(new RxSubscriber<List<CommentEntity>>(Api.getLtCommentByConId(c_id, "1", "20")) {
            @Override
            protected void _onNext(List<CommentEntity> list) {
                if (list != null && list.size() > 0) {
                    getCommentList().clear();
                    getCommentList().addAll(list);
                    mView.updateComment();
                }
            }
        });
    }

    @Override
    public void addComment(String id, String content) {
        addRx2Destroy(new RxSubscriber<Object>(Api.ltAddComment(id, "0", content)) {
            @Override
            protected void _onNext(Object count) {
                toast("评论成功");
                mView.commentSuc(count.toString());
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }
}
