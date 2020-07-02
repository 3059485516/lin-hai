package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.LtDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;

public class LtDetailPresenter extends LtDetailContract.Presenter {
    private List<LtDetailEntity.CaSourceList> mCaSourceInfoList = new ArrayList<>();
    private List<NodeContent> mCommentList = new ArrayList<>();

    public LtDetailPresenter(LtDetailContract.View view) {
        super(view);
    }

    @Override
    public void getCAInfo(String code) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<LtDetailEntity>(Api.getCAInfo(code)) {
            @Override
            protected void _onNext(LtDetailEntity o) {
                mView.dismissDialog();
                if (o != null) {
                    if (o.getCa_source_list() != null) {
                        mCaSourceInfoList.clear();
                        mCaSourceInfoList.addAll(o.getCa_source_list());
                    }
                    if (o.getCon_list() != null) {
                        mCommentList.clear();
                        mCommentList.addAll(o.getCon_list());
                    }
                    mView.updateView(o);
                }
            }
        });
    }

    public List<LtDetailEntity.CaSourceList> getSourceList() {
        return mCaSourceInfoList;
    }

    public List<NodeContent> getCommentList() {
        return mCommentList;
    }
}
