package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TheoryContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class TheoryPresenter extends TheoryContract.Presenter {
    private List<NodeContent> mActionList = new ArrayList<>();
    private String mNodeId;

    public TheoryPresenter(TheoryContract.View view, String id) {
        super(view);
        mNodeId = id;
    }

    @Override
    public List<NodeContent> getAdvanceTopList() {
        return mActionList;
    }

    @Override
    public void getAdvanceList() {
        Map<String, String> params = new HashMap<>();
        params.put("page", getPage() + "");
        params.put("rows", "2");
        params.put("node_id", mNodeId);
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getContentList(Constant.DEFAULT_REGION_CODE, "n", "y", mNodeId, getPage() + "", PAGE_SIZE + "")) {
            @Override
            protected void _onNext(List<NodeContent> list) {
                if (list != null) {
                    mActionList.clear();
                    if (list.size() > 1) {
                        mActionList.addAll(list.subList(0, 1));     //只截取前两条数据
                    } else {
                        mActionList.addAll(list);
                    }
                    mView.updateActionList();
                }
            }
        });
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(Constant.DEFAULT_REGION_CODE, "n", "n", mNodeId, getPage() + "", PAGE_SIZE + ""));
        getAdvanceList();
    }
}
