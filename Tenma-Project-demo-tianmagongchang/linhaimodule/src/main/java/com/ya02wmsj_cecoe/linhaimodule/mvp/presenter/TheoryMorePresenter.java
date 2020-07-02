package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TheoryMoreContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class TheoryMorePresenter extends TheoryMoreContract.Presenter {
    private String mNodeId;

    public TheoryMorePresenter(TheoryMoreContract.View view, String node_id) {
        super(view);
        mNodeId = node_id;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(Constant.DEFAULT_REGION_CODE, "n","y", mNodeId, getPage() + "", PAGE_SIZE + ""));
    }
}
