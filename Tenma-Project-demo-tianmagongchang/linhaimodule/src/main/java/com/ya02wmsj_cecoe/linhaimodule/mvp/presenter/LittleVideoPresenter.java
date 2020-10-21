package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LittleVideoContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/22 9:59 AM
 * desc   : EMPTY
 * ================================================
 */
public class LittleVideoPresenter extends LittleVideoContract.Presenter {
    public LittleVideoPresenter(LittleVideoContract.View view, Intent intent) {
        super(view, intent);
    }

    @Override
    public void like(String id, TextView view, int position) {
        addRx2Destroy(new RxSubscriber<Object>(Api.likeContent(id)) {
            @Override
            protected void _onNext(Object o) {
                NodeContent nodeContent = getData().get(position);
                int thumb_num = nodeContent.getThumb_num();
                if (isHasLike(nodeContent)) {
                    thumb_num--;
                    if (thumb_num < 0) {
                        thumb_num = 0;
                    }
                } else {
                    thumb_num++;
                }
                nodeContent.setThumb_num(thumb_num);
                nodeContent.setThumb(isHasLike(nodeContent) ? 0 : 1);
                mView.changeLikeState(true, view, position);
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                mView.changeLikeState(false, view, position);
            }
        });
    }

    @Override
    public void collect(String id, TextView view, int position) {
        addRx2Destroy(new RxSubscriber<Object>(Api.collectContent(id)) {
            @Override
            protected void _onNext(Object o) {
                NodeContent nodeContent = getData().get(position);
                int collect_num = nodeContent.getCollect_num();
                if (isHasCollect(nodeContent)) {
                    collect_num--;
                    if (collect_num < 0) {
                        collect_num = 0;
                    }
                } else {
                    collect_num++;
                }
                nodeContent.setCollect_num(collect_num);
                nodeContent.setCollect(isHasCollect(nodeContent) ? 0 : 1);
                mView.changeCollectState(true, view, position);
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                mView.changeCollectState(false, view, position);
            }
        });
    }

    @Override
    public void share(String id) {
        addRx2Destroy(new RxSubscriber<Object>(Api.shareContent(id)) {
            @Override
            protected void _onNext(Object o) {
                mView.changeShareState(true);
            }

            @Override
            protected void _onError(String code) {
                mView.changeShareState(false);
            }
        });
    }


    @Override
    public void requestData(boolean isRefresh) {
        Map<String, Object> map = new HashMap<>();
        map.put("region_code", getRegionCode());
        // map.put("top_status", "n");
        map.put("is_announce", "n");
        map.put("node_id", getNodeId());
        map.put("c_id", getContentId());
        map.put("page", getPage());
        map.put("rows", getRow());
        addDataObservable(Api.getVideoList(map), isRefresh);
    }

    @Override
    public void getCommentById(String c_id, CommentAdapter adapter, ProgressBar progressBar) {
        addRx2Destroy(new RxSubscriber<List<CommentEntity>>(Api.getCommentById(c_id, "")) {
            @Override
            protected void _onNext(List<CommentEntity> list) {
                if (list != null) {
                    getCommentList().clear();
                    getCommentList().addAll(list);
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                    if (progressBar != null) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                if (progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void addComment(String id, String content, TextView tvComment) {
        addRx2Destroy(new RxSubscriber<String>(Api.addComment(id, content)) {
            @Override
            protected void _onNext(String count) {
                toast("评论成功");
                mView.commentSuc(count, tvComment);
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }
}
