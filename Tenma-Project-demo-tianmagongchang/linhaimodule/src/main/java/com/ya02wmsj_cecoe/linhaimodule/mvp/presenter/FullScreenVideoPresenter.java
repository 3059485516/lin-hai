package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FullScreenVideoContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullScreenVideoPresenter extends FullScreenVideoContract.Presenter {
    public FullScreenVideoPresenter(FullScreenVideoContract.View view) {
        super(view);
    }

    @Override
    public void getDetailContent(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getContentDetail(map)) {
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
        addRx2Destroy(new RxSubscriber<Object>(Api.likeContent(id)) {
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
        addRx2Destroy(new RxSubscriber<Object>(Api.collectContent(id)) {
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
        addRx2Destroy(new RxSubscriber<Object>(Api.shareContent(id)) {
            @Override
            protected void _onNext(Object o) {
                mView.shareSuc();
            }

            @Override
            protected void _onError(String code) {
//                super._onError(code);
            }
        });
    }

    @Override
    public void getCommentById(String c_id) {
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
    public void addComment(String id, String content) {
        addRx2Destroy(new RxSubscriber<String>(Api.addComment(id, content)) {
            @Override
            protected void _onNext(String count) {
                toast("评论成功");
                mView.commentSuc(count);
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }
}
