package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.CultureContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class CulturePresenter extends CultureContract.Presenter {
    private String mNodeId;
    private List<NodeContent> mTopList = new ArrayList<>();

    public CulturePresenter(CultureContract.View view, String node_id) {
        super(view);
        mNodeId = node_id;
    }

    @Override
    public void getTopListData() {
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getContentList(
                Constant.DEFAULT_REGION_CODE, "n","y", mNodeId, getPage() + "", PAGE_SIZE + "")) {
            @Override
            protected void _onNext(List<NodeContent> list) {
                if (list != null) {
                    mTopList.clear();
                    mTopList.addAll(list);
                    mView.updateTopList();
                }
            }
        });
    }

    @Override
    public List<NodeContent> getTopList() {
        return mTopList;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        getTopListData();
        setDataSource(Api.getContentList(Constant.DEFAULT_REGION_CODE, "n","n", mNodeId, getPage() + "", PAGE_SIZE + ""));
    }
}
